package io.wanyxkhalil.abstinence.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResp {
    private boolean success;
    private String code;
    private String message;
    private BaseData data;

    private BaseResp(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static BaseResp success() {
        return success(new BaseData());
    }

    public static BaseResp success(BaseData data) {
        BaseResp resp = new BaseResp(true, ResponseCode.NORMAL.getCode(), ResponseCode.NORMAL.getMessage());
        resp.setData(data);
        return resp;
    }

    public static BaseResp fail(ResponseCode code) {
        return new BaseResp(false, code.getCode(), code.getMessage());
    }

    public static BaseResp fail(String errorCode, String errorMessage) {
        return new BaseResp(false, errorCode, errorMessage);
    }

    public static class BaseData {
    }
}
