package kr.co.gradesmanagement.infra.exception;

import kr.co.gradesmanagement.infra.model.ErrorCode;

public class NotFoundException extends CustomException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
