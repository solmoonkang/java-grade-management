package kr.co.gradesmanagement.service;

import kr.co.gradesmanagement.infra.exception.InvalidRequestException;
import kr.co.gradesmanagement.infra.exception.error.ErrorCode;
import kr.co.gradesmanagement.model.domain.Grade;
import kr.co.gradesmanagement.model.domain.Student;
import kr.co.gradesmanagement.model.dto.request.StudentReqDTO;
import kr.co.gradesmanagement.model.dto.response.StudentResDTO;
import kr.co.gradesmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 6;

    private final StudentRepository studentRepository;

    public void createStudent(StudentReqDTO.CREATE create) {
        final Student student = Student.toStudentEntity(create);
        checkStudentGradeExists(String.valueOf(create.getGrade()));
        checkStudentYearExists(create.getYear());
        studentRepository.save(student);
    }

    public List<StudentResDTO.READ> findAllStudents() {
        return studentRepository.findAllStudents().stream()
                .sorted(Comparator.comparing(Student::getGrade))
                .map(Student::toReadDto)
                .collect(Collectors.toList());
    }

    public List<StudentResDTO.READ> findStudentByGrade(Grade grade) {
        return studentRepository.findStudentByGrade(grade).stream()
                .map(Student::toReadDto)
                .collect(Collectors.toList());
    }

    private void checkStudentYearExists(int year) {
        if (year < MIN_GRADE || year >= MAX_GRADE) {
            throw new InvalidRequestException(ErrorCode.FAIL_INVALID_YEAR);
        }
    }

    private void checkStudentGradeExists(String grade) {
        boolean isMatchGrade = Arrays.stream(Grade.values())
                .anyMatch(validGrade -> validGrade.name().equals(grade));

        if (!isMatchGrade) {
            throw new InvalidRequestException(ErrorCode.FAIL_INVALID_GRADE);
        }
    }
}
