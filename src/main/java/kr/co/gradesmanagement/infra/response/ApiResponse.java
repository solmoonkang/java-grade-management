package kr.co.gradesmanagement.infra.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co.gradesmanagement.infra.exception.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ApiResponse<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Status status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Metadata metadata;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

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
