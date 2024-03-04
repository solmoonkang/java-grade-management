package kr.co.gradesmanagement.infra.exception;

import kr.co.gradesmanagement.infra.model.ErrorCode;

public class CustomException extends RuntimeException {

    private ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomException(String message) {
        super(message);
    }
}
