package com.start.test.go;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @description: 自然语言
 * @author: zhanghuiyong
 * @create: 2019-12-30 17:57
 */
public class NLPTest {
    static String address = "杨行镇杨北村（小陈巷）37号1层102室";

    public static void main(String[] args) {

        String[] result = address.split("\\d+号");
        System.out.println(Arrays.toString(Arrays.stream(result).map(s -> s.trim()).collect(Collectors.toList()).toArray()));

        String[] testCase = new String[]{
                result[0],
        };
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        for (String sentence : testCase) {
            List<Term> termList = segment.seg(sentence);
            termList.stream().forEach(term -> System.out.println(term.word));
        }
    }
}
