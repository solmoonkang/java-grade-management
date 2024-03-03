package kr.co.gradesmanagement.repository;

import kr.co.gradesmanagement.domain.Grade;
import kr.co.gradesmanagement.domain.Student;
import kr.co.gradesmanagement.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void findStudentById() {
        // Given
        Student student = Student.builder().name("studentA").grade(Grade.A).year(1).build();
        Student savedStudent = studentRepository.save(student);

        // When
        Student findStudent = studentRepository.findStudentById(savedStudent.getId());

        // Then
        assertNotNull(findStudent);
        assertEquals(savedStudent.getId(), findStudent.getId());
        assertEquals("studentA", findStudent.getName());
        assertEquals(Grade.A, findStudent.getGrade());
        assertEquals(1, findStudent.getYear());
    }

    @Test
    void findAllStudents() {
        // Given
        Student student1 = Student.builder().id(1L).name("studentA").grade(Grade.A).year(1).build();
        Student student2 = Student.builder().id(2L).name("studentB").grade(Grade.C).year(3).build();

        studentRepository.save(student1);
        studentRepository.save(student2);

        // When
        List<Student> students = studentRepository.findAllStudents();

        // Then
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student2));
    }

    @Test
    void findStudentByGrade() {
        // Given
        Grade testGrade = Grade.A;
        Student student1 = Student.builder().id(1L).name("studentA").grade(testGrade).year(1).build();
        Student student2 = Student.builder().id(2L).name("studentB").grade(Grade.B).year(2).build();
        Student student3 = Student.builder().id(3L).name("studentC").grade(testGrade).year(3).build();

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        // When
        List<Student> students = studentRepository.findStudentByGrade(testGrade);

        // Then
        assertEquals(2, students.size());
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student3));
    }
}
