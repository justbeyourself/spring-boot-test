package com.start.test.utils;

/**
 * @author st  <hushunting@startdt.com>
 * @date 2018/7/16 下午4:16
 */
public class AmountCalculationUtils {

    /**
     * 分转换成元
     *
     * @param pointPrice
     * @return
     */
    public static String convertPointToYuan(long pointPrice) {
        if (pointPrice == 0) {
            return "0";
        }
        return String.valueOf(ArithmeticUtils.div(pointPrice, 100, 2));
    }

}
