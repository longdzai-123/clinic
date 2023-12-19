package vn.oceantech.l3pre.common;

import lombok.Getter;
import vn.oceantech.l3pre.exception.ErrorMessage;

@Getter
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Response<T> buildResponse(ErrorMessage errorMessage) {
        Response<T> response = new Response<>();
        response.code = errorMessage.getCode();
        response.message = errorMessage.getMessage();
        return response;
    }

    public static <T> Response<T> buildResponse(T data) {
        Response<T> response = new Response<>();
        response.data = data;
        response.code = 200;
        return response;
    }



}
