package kr.co.gradesmanagement.infra.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Getter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private HttpStatus status;

    private String message;

    private Optional<T> data;

    private boolean isSuccessful;

    public static <T> ApiResponse<T> successMessage(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .data(Optional.empty())
                .isSuccessful(true)
                .build();
    }

    public static <T> ApiResponse<T> failureMessage(ErrorCode errorCode, T data) {
        return ApiResponse.<T>builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .data(Optional.ofNullable(data))
                .isSuccessful(false)
                .build();
    }
}
