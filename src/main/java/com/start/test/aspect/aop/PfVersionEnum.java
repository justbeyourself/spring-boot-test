package com.start.test.aspect.aop;

/**
 * @author hhh <hushunting@startdt.com>
 * @date 2019/10/12 11:48 上午
 **/
public enum PfVersionEnum {

    STORE("门店版", "0"), MERCHANT("百购版", "1");

    private String text;

    private String code;

    PfVersionEnum(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PfVersionEnum getPfVersionByCode(String code) {
        PfVersionEnum[] values = PfVersionEnum.values();
        for (PfVersionEnum type : values) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }


}
