package kr.co.gradesmanagement.infra.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private HttpStatus status;

    private String message;

    private T data;

    private boolean isSuccessful;

    public static <T> ApiResponse<T> successMessage(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .data(null)
                .isSuccessful(true)
                .build();
    }

    public static <T> ApiResponse<T> successMessageWithData(ErrorCode errorCode, T data) {
        return ApiResponse.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .data(data)
                .isSuccessful(true)
                .build();
    }

    public static <T> ApiResponse<T> failureMessage(ErrorCode errorCode, T data) {
        return ApiResponse.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .data(data)
                .isSuccessful(false)
                .build();
    }
}
