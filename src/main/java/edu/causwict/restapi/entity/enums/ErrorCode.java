package edu.causwict.restapi.entity.enums;

public enum ErrorCode {
    NO_ERROR("오류가 발견되지 않았습니다"),
    TITLE_IS_EMPTY("제목이 비어 있습니다"),
    TITLE_IS_TOO_LONG("제목이 너무 깁니다"),
    TITLE_ALREADY_EXIST("같은 제목이 이미 존재합니다"),
    SPAMMING("너무 짧은 간격으로 글을 작성했습니다");

    final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
