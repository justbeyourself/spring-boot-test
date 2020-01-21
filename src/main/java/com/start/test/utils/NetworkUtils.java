package com.start.test.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author st  <hushunting@startdt.com>
 * @date 2019/2/19 7:57 PM
 */
public class NetworkUtils {

    private static final Pattern DOMAIN_PATTERN = Pattern.compile("([-a-zA-Z0-9]+(\\.[-a-zA-Z0-9]+)+)");

    public static String getDomain(String domain) {
        Matcher matcher = DOMAIN_PATTERN.matcher(domain);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

}
