package com.meihaofenqi.common.webutilsboot.exception;

public class WebException extends RuntimeException {

    private WebException(String message) {
        super(message);
    }

    public static WebException build(String messgae) {
        return new WebException(messgae);
    }
}
