package com.thanhtran.models;

import java.util.List;
import java.util.Map;

public class Response {
    public int code;
    public String message;
    Object data;

    public Response(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
