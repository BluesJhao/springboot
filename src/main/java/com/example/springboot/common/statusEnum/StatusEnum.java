package com.example.springboot.common.statusEnum;

/**
 * Desc ：
 * Created by JHAO on 2017/10/27.
 */
public enum StatusEnum {

    CODE_0000("0000", "正常"),
    CODE_9998("9998", "参数异常"),
    CODE_9999("9999", "系统异常!"),
    ;
    private String code;
    private String message;

    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static StatusEnum getByCode(String code) {

        for (StatusEnum e : values()) {
            if (e.code.equalsIgnoreCase(code)) {
                return e;
            }
        }
        return null;
    }
}
