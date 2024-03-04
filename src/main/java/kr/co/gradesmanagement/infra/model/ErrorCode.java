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

    FAIL_BAD_REQUEST(HttpStatus.BAD_REQUEST, "⚠️ 해당 요청은 잘못된 요청입니다."),
    FAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "⚠️ 찾을 수 없는 요청입니다."),
    FAIL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "⚠️ 서버 내부 문제로 인해 요청이 처리되지 않았습니다.");

    private HttpStatus status;

    private String message;
}
