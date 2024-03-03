package kr.co.gradesmanagement.repository;

import kr.co.gradesmanagement.domain.Grade;
import kr.co.gradesmanagement.domain.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentRepositoryTest {

    StudentRepository studentRepository = new StudentRepository();

    @AfterEach
    void afterEach() {
        studentRepository.clearStore();
    }

    @Test
    void save() {
        // Given
        Student student = Student.builder().name("studentA").grade(Grade.A).year(1).build();

        // When
        Student savedStudent = studentRepository.save(student);

        // Then
        assertNotNull(savedStudent.getId());
        assertThat("studentA").isEqualTo(savedStudent.getName());
        assertThat(Grade.A).isEqualTo(savedStudent.getGrade());
        assertThat(1).isEqualTo(savedStudent.getYear());
    }
}
