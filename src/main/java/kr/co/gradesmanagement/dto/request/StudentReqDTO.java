package kr.co.gradesmanagement.dto.request;

import kr.co.gradesmanagement.domain.Grade;
import lombok.*;

public class StudentReqDTO {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        private String name;

        private Grade grade;

        private int year;
    }
}
