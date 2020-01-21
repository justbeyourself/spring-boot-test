package com.start.test.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final String SSL_CONTEXT_TYPE = "TLS";

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 计算签名，参数分别是参数对以及密钥
     *
     * @param requestParams 参数对，即参与计算签名的参数
     * @param secretKey     密钥
     * @return 签名字符串
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String doHttpSignature(Map<String, String> requestParams, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        List<String> paramList = new ArrayList<String>();
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            paramList.add(entry.getKey() + "=" + entry.getValue());
        }
        Collections.sort(paramList);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < paramList.size(); i++) {
            if (i > 0) {
                sb.append('&');
            }
            sb.append(paramList.get(i));
        }
        return macSignature(sb.toString(), secretKey);
    }

    /**
     * @param text      要签名的文本
     * @param secretKey 阿里云MQ secretKey
     * @return 加密后的字符串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String macSignature(String text, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {
        Charset charset = Charset.forName("UTF-8");
        String algorithm = "HmacSHA1";
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(secretKey.getBytes(charset), algorithm));
        byte[] bytes = mac.doFinal(text.getBytes(charset));
        return new String(Base64.encodeBase64(bytes), charset);
    }

    private static RequestConfig requestConfig = RequestConfig.custom()
            //从连接池中获取连接的超时时间
            .setConnectionRequestTimeout(1000)
            //与服务器连接超时时间：httpclient会创建一个异步线程用以创建socket连接，此处设置该socket的连接超时时间
            .setConnectTimeout(10000)
            //socket读数据超时时间：从服务器获取响应数据的超时时间
            .setSocketTimeout(10000)
            .build();


    private static Registry<ConnectionSocketFactory> connectionSocketFactoryRegistry() throws NoSuchAlgorithmException, KeyManagementException {
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
        SSLContext context = SSLContext.getInstance(SSL_CONTEXT_TYPE);
        context.init(null, new TrustManager[]{xtm}, null);
        SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", scsf).build();

        return sfr;
    }


    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() throws KeyManagementException, NoSuchAlgorithmException {
        PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager(connectionSocketFactoryRegistry());
        // 连接池中最大连接数
        pcm.setMaxTotal(200);

        // 分配给同一个route(路由)最大的并发连接数。
        // route：运行环境机器 到 目标机器的一条线路。
        // 举例来说，我们使用HttpClient的实现来分别请求 www.baidu.com 的资源和 www.bing.com 的资源那么他就会产生两个route。
//        pcm.setDefaultMaxPerRoute(5);

        return pcm;
    }


    /**
     * 创建HTTPS 客户端
     *
     * @return 单例模式的客户端
     * @throws KeyStoreException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static HttpClient httpClient = null;

    private static HttpClient getHttpsClient() throws NoSuchAlgorithmException, KeyManagementException {
        if (httpClient != null) {
            return httpClient;
        }

        httpClient = HttpClientBuilder.create()
                .setConnectionManager(poolingHttpClientConnectionManager())
                .setDefaultRequestConfig(requestConfig)
                .build();
        return httpClient;
    }


    /**
     * httpclient 非单例
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static HttpClient customHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        return HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(poolingHttpClientConnectionManager()).build();
    }


    public static HttpClient createHttpsClient() throws KeyManagementException, NoSuchAlgorithmException {
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                    throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
        SSLContext context = SSLContext.getInstance(SSL_CONTEXT_TYPE);
        context.init(null, new TrustManager[]{xtm}, null);
        SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", scsf).build();
        PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager(sfr);

        return HttpClientBuilder.create()
                .setConnectionManager(pcm)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * 发起Https Get请求，并得到返回的JSON响应
     *
     * @param url    接口Url
     * @param params 参数u对
     */
    public static String httpsGet(String url, Map<String, String> params, RequestConfig requestConfig) {
        String result = null;
        HttpGet request = null;
        try {
            HttpClient client = getHttpsClient();
            String paramUrl = "";
            if (params != null) {
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                paramUrl = "?" + URLEncodedUtils.format(urlParameters, Charset.forName("UTF-8"));
            }
            request = new HttpGet(url + paramUrl);
            if (requestConfig != null) {
                request.setConfig(requestConfig);
            }
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (request != null) {
                request.releaseConnection();
            }
        }
        return result;
    }

    /**
     * 发起Https Get请求，并得到返回的JSON响应
     *
     * @param url    接口Url
     * @param params 参数u对
     */
    public static String httpsGet(String url, Map<String, String> params) {
        String result = null;
        HttpGet request = null;
        try {
            HttpClient client = getHttpsClient();
            String paramUrl = "";
            if (params != null) {
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                paramUrl = "?" + URLEncodedUtils.format(urlParameters, Charset.forName("UTF-8"));
            }
            request = new HttpGet(url + paramUrl);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (request != null) {
                request.releaseConnection();
            }
        }
        return result;
    }

    /**
     * 发起Https Get请求，并得到返回的JSON响应
     *
     * @param url    接口Url
     * @param params 参数u对
     */
    public static HttpResponse httpsGet(String url, Map<String, String> params, Map<String, String> headers) {
        HttpResponse response = null;
        HttpGet request = null;
        try {
            HttpClient client = getHttpsClient();
            String paramUrl = "";
            if (params != null) {
                List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                paramUrl = "?" + URLEncodedUtils.format(urlParameters, Charset.forName("UTF-8"));
            }
            request = new HttpGet(url + paramUrl);
            if (headers != null && headers.size() != 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    request.setHeader(entry.getKey(), entry.getValue());
                }
            }
            response = client.execute(request);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (request != null) {
                request.releaseConnection();
            }
        }
        return response;
    }

    /**
     * 不使用链接池
     *
     * @param url
     * @return
     */
    public static String simpleHttpGet(String url) throws IOException {
        CloseableHttpClient httpclient = null;
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } finally {
            if (response != null) {
                response.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        }
        return null;
    }


    public static String httpsGet(String url) {
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        try {
            HttpClient client = customHttpClient();
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            logger.error("GET error: " + url, e);
        } finally {
            request.releaseConnection();
        }
        return null;
    }


    public static String httpsGet(String url, RequestConfig config) {
        HttpGet request = new HttpGet(url);
        request.setConfig(config);
        try {
            HttpClient client = customHttpClient();
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            logger.error("GET error: " + url, e);
        } finally {
            request.releaseConnection();
        }
        return null;
    }


    /**
     * 发送内容是json字符串
     *
     * @param url    请求的方法名url
     * @param params 参数表
     * @return 如果请求成功则返回JSON, 否则抛异常或者返回空
     */
    public static String httpsPostJson(String url, JSONObject params) {
        try {
            return httpsPostJson(url, params, false);
        } catch (UnknownHostException e) {
            logger.error("", e);
        }
        return null;
    }

    public static String httpsPostJson(String url, JSONObject params, boolean isFlushDns) throws UnknownHostException {
        HttpPost request = new HttpPost(url);
        String jsonResult = null;
        try {
            //发送get请求
//            HttpClient client = getHttpsClient();
            HttpClient client = customHttpClient();
            if (params != null) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(params.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                request.setEntity(entity);
            }
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                jsonResult = EntityUtils.toString(response.getEntity());
            }
        } catch (UnknownHostException e1) {
            throw e1;
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            request.releaseConnection();
        }
        return jsonResult;
    }


    public static String httpsPostJson(String url, String jsonStr) {
        HttpPost request = new HttpPost(url);
        String jsonResult = null;
        try {
//            HttpClient client = getHttpsClient();
            HttpClient client = customHttpClient();

            if (!StringUtils.isEmpty(jsonStr)) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonStr, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                request.setEntity(entity);
            }
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                jsonResult = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            request.releaseConnection();
        }
        return jsonResult;
    }

    public static HttpResponse httpsPostJson(String url, String jsonStr, Map<String, String> headers) {
        HttpPost request = new HttpPost(url);
        HttpResponse response = null;
        try {
            HttpClient client = customHttpClient();

            if (!StringUtils.isEmpty(jsonStr)) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonStr, "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                request.setEntity(entity);
            }
            if (headers != null && headers.size() != 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    request.setHeader(entry.getKey(), entry.getValue());
                }
            }
            response = client.execute(request);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            request.releaseConnection();
        }
        return response;
    }


    /**
     * 工具方法，发送一个http post请求，并尝试将响应转换为JSON
     *
     * @param url    请求的方法名url
     * @param params 参数表
     * @return 如果请求成功则返回JSON, 否则抛异常或者返回空
     */
    public static JSONObject httpsPost(String url, Map<String, String> params) {
        JSONObject jsonResult = null;
        HttpPost request = new HttpPost(url);
        try {
            //发送get请求
            HttpClient client = getHttpsClient();
            List<NameValuePair> urlParameters = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
            request.setEntity(postParams);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                jsonResult = JSON.parseObject(strResult);
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            request.releaseConnection();
        }
        return jsonResult;
    }

}
