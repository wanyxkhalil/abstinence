package io.wanyxkhalil.abstinence.wub.exception;

import io.wanyxkhalil.abstinence.webdomain.response.BaseResp;
import io.wanyxkhalil.abstinence.webdomain.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    /**
     * 自定义异常返回代码
     */
    private static final String WEB_EXCEPTION_RESPONSE_CODE = "9001";

    /**
     * 自定义异常返回
     *
     * @param exception 异常
     * @return 返回
     */
    @ExceptionHandler(WebException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResp webException(WebException exception) {
        return BaseResp.fail(WEB_EXCEPTION_RESPONSE_CODE, exception.getMessage());
    }

    /**
     * 通用异常返回
     *
     * @param exception 异常
     * @return 返回
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResp exception(Exception exception) {
        log.error("raised exception: ", exception);
        return BaseResp.fail(ResponseCode.EXCEPTION_ERROR);
    }

    /**
     * 请求参数绑定、验证错误
     *
     * @param ex 异常
     * @return 返回
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResp validationError(MethodArgumentNotValidException ex) {
        log.error("raised BindException: ", ex);

        String msg = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findAny()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(ResponseCode.BAD_REQUEST_ERROR.getMessage());

        return BaseResp.fail(ResponseCode.BAD_REQUEST_ERROR.getCode(), msg);
    }

    /**
     * 请求参数读取错误
     *
     * @param ex 异常
     * @return 返回
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResp validationError(HttpMessageNotReadableException ex) {
        log.error("raised Exception: ", ex);

        return BaseResp.fail(ResponseCode.BAD_REQUEST_ERROR.getCode(), ex.getMessage());
    }
}
