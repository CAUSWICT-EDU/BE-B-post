package edu.causwict.restapi.repository.enums;

public enum ErrorCode {
    NO_ERROR("오류가 발견되지 않았습니다"),
    NO_SUCH_ID_POST("해당하는 ID의 Post가 없습니다");

    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
