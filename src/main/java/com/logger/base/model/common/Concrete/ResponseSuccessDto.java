package com.logger.base.model.common.Concrete;

public class ResponseSuccessDto {
    private final boolean success;
    private final String message;

    public ResponseSuccessDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
