package kr.co.gradesmanagement.service;

import kr.co.gradesmanagement.domain.Grade;
import kr.co.gradesmanagement.domain.Student;
import kr.co.gradesmanagement.dto.request.StudentReqDTO;
import kr.co.gradesmanagement.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentServiceTest {

    private final StudentRepository studentRepository = new StudentRepository();
    private final StudentService studentService = new StudentService(studentRepository);

    @AfterEach
    void afterEach() {
        studentRepository.clearStore();
    }

    @Test
    void createStudent() {
        // Given
        StudentReqDTO.CREATE create = StudentReqDTO.CREATE.builder()
                .name("studentA")
                .grade(Grade.A)
                .year(1)
                .build();

        // When
        studentService.createStudent(create);
        Student findStudent = studentRepository.findStudentById(1L);

        // Then
        assertNotNull(findStudent);
        assertEquals("studentA", findStudent.getName());
        assertEquals(Grade.A, findStudent.getGrade());
        assertEquals(1, findStudent.getYear());
    }
}
