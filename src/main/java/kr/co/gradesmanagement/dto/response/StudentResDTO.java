package kr.co.gradesmanagement.dto.response;

import kr.co.gradesmanagement.domain.Grade;
import lombok.*;

public class StudentResDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ {

        private String name;

        private Grade grade;

        private int year;
    }
}
