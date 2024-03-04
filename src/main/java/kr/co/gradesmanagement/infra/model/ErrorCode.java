package kr.co.gradesmanagement.infra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {

    SUCCESS_EXECUTE(HttpStatus.OK, "✅ 요청이 성공적으로 처리되었습니다."),
    SUCCESS_CREATED(HttpStatus.CREATED, "✅ 요청이 성공적으로 생성되었습니다."),

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "⚠️ 서버 내부 문제로 인해 요청이 처리되지 않았습니다.");

    private HttpStatus status;

    private String message;
}
