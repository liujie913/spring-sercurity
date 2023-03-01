package com.lj.sercurity.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class Response implements Serializable {

    private boolean success;
    private Object data;
    private String errorCode;
    private String errorMessage;

    public static Response buildSuccess() {
        Response response = new Response();
        response.success = true;
        return response;
    }

    public static Response buildFailure() {
        Response response = new Response();
        response.success = false;
        return response;
    }

}
