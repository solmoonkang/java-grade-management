package kr.co.gradesmanagement.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    private Long id;

    private String name;

    private Grade grade;

    private int year;

    @Builder(toBuilder = true)
    private Student(Long id,
                    String name,
                    Grade grade,
                    int year) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.year = year;
    }
}
