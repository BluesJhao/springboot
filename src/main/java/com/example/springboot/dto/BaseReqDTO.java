package com.example.springboot.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Desc ：
 * Created by JHAO on 2017/10/26.
 */
public class BaseReqDTO<T> implements Serializable {

    @NotNull(message = "渠道标识不能为空")
    private String channel;

    private T data;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseReqDTO{" +
                "channel='" + channel + '\'' +
                ", data=" + data +
                '}';
    }
}
