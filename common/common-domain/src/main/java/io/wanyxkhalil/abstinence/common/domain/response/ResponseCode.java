package io.wanyxkhalil.abstinence.common.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    NORMAL("0000", "成功"),

    BAD_REQUEST_ERROR("99999995", "请求参数输入错误"),
    VALIDATION_ERROR("99999996", "请求参数验证错误"),
    EXCEPTION_ERROR("99999997", "程序异常"),
    UNKNOWN_ERROR("99999998", "未知错误"),
    FAILURE("99999999", "失败"),;

    private String code;
    private String message;
}