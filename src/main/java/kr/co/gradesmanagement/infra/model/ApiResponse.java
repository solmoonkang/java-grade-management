package kr.co.gradesmanagement.infra.model;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {

    private Status status;

    private Metadata metadata;

    @Nullable
    private T result;

    @Getter
    @AllArgsConstructor
    public static class Status {

        private int httpStatus;

        private String message;
    }

    @Getter
    @AllArgsConstructor
    public static class Metadata {

        private int resultCount;
    }

    public static <T> ApiResponse<T> successMessage(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .status(new Status(errorCode.getStatus().value(), errorCode.getMessage()))
                .build();
    }

    public static <T> ApiResponse<T> successMessageWithData(ErrorCode errorCode, T result) {
        return ApiResponse.<T>builder()
                .status(new Status(errorCode.getStatus().value(), errorCode.getMessage()))
                .metadata(new Metadata(((List<?>) result).size()))
                .result(result)
                .build();
    }

    public static ApiResponse failureMessage(ErrorCode errorCode, String message) {
        return ApiResponse.builder()
                .status(new Status(errorCode.getStatus().value(), message))
                .build();
    }
}
