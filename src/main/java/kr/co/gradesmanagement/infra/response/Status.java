package kr.co.gradesmanagement.infra.response;

public record Status(
        int httpStatus,

        String message
) {
}
