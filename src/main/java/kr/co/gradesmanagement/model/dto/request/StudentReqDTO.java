package kr.co.gradesmanagement.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.co.gradesmanagement.model.domain.Grade;
import lombok.*;

public class StudentReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotBlank(message = "학생 이름은 비어 있을 수 없습니다.")
        private String name;

        @NotBlank(message = "학생 성적은 비어 있을 수 없습니다.")
        private Grade grade;

        @NotNull(message = "학생 학년은 비어 있을 수 없습니다.")
        private int year;
    }
}
