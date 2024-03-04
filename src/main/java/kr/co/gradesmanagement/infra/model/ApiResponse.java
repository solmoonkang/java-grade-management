package kr.co.gradesmanagement.infra.model;

import jakarta.annotation.Nullable;
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

    @Nullable
    private T data;

    public static <T> ApiResponse<T> successMessage(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> successMessageWithData(ErrorCode errorCode, T data) {
        return ApiResponse.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> failureMessage(ErrorCode errorCode, T data) {
        return ApiResponse.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .data(data)
                .build();
    }
}
