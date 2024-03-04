package kr.co.gradesmanagement.infra.handler;

import kr.co.gradesmanagement.infra.exception.CustomException;
import kr.co.gradesmanagement.infra.exception.InvalidRequestException;
import kr.co.gradesmanagement.infra.exception.NotFoundException;
import kr.co.gradesmanagement.infra.model.ApiResponse;
import kr.co.gradesmanagement.infra.model.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ApiResponse> handleNotFoundException(NotFoundException e) {
        log.warn("====== HandleNotFoundException =====", e);
        ApiResponse apiResponse = ApiResponse.failureMessage(ErrorCode.FAIL_NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<ApiResponse> handleInvalidRequestException(InvalidRequestException e) {
        log.warn("====== HandleInvalidRequestException =====", e);
        ApiResponse apiResponse = ApiResponse.failureMessage(ErrorCode.FAIL_BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ApiResponse> handleCustomException(CustomException e) {
        log.warn("====== HandleCustomException =====", e);
        ApiResponse apiResponse = ApiResponse.failureMessage(ErrorCode.FAIL_SERVER_ERROR, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
