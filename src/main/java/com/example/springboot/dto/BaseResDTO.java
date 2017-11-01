package com.example.springboot.dto;

import com.example.springboot.common.statusEnum.StatusEnum;

import java.io.Serializable;

/**
 * Desc ：
 * Created by JHAO on 2017/10/26.
 */
public class BaseResDTO<T> implements Serializable {

    private String code;

    private String msg;

    private T Result;

    public BaseResDTO(StatusEnum statusEnum) {
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMessage();
    }

    public BaseResDTO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResDTO(String code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        Result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return Result;
    }

    public void setResult(T result) {
        Result = result;
    }

    @Override
    public String toString() {
        return "BaseResDTO{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", Result=" + Result +
                '}';
    }
}
