package kr.co.gradesmanagement.service;

import kr.co.gradesmanagement.model.domain.Grade;
import kr.co.gradesmanagement.model.domain.Student;
import kr.co.gradesmanagement.model.dto.request.StudentReqDTO;
import kr.co.gradesmanagement.model.dto.response.StudentResDTO;
import kr.co.gradesmanagement.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void findAllStudents() {
        // Given
        Student student1 = Student.builder().id(1L).name("studentA").grade(Grade.A).year(1).build();
        Student student2 = Student.builder().id(2L).name("studentB").grade(Grade.C).year(3).build();
        Student student3 = Student.builder().id(3L).name("studentC").grade(Grade.B).year(2).build();

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        // When
        List<StudentResDTO.READ> studentDTOs = studentService.findAllStudents();

        // Then
        assertEquals(3, studentDTOs.size());
        assertEquals(student1.getName(), studentDTOs.get(0).getName());
        assertEquals(student3.getName(), studentDTOs.get(1).getName());
        assertEquals(student2.getName(), studentDTOs.get(2).getName());
    }

    @Test
    void findStudentByGrade() {
        // Given
        Grade testGrade = Grade.A;
        Student student1 = Student.builder().id(1L).name("studentA").grade(testGrade).year(1).build();
        Student student2 = Student.builder().id(2L).name("studentB").grade(testGrade).year(2).build();

        studentRepository.save(student1);
        studentRepository.save(student2);

        // When
        List<StudentResDTO.READ> studentDTOs = studentService.findStudentByGrade(testGrade);

        // Then
        assertEquals(2, studentDTOs.size());
        assertEquals(student1.getGrade(), studentDTOs.get(0).getGrade());
        assertEquals(student2.getGrade(), studentDTOs.get(1).getGrade());
    }
}
