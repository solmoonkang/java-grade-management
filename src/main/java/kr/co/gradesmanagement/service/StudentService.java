package kr.co.gradesmanagement.service;

import kr.co.gradesmanagement.domain.Grade;
import kr.co.gradesmanagement.domain.Student;
import kr.co.gradesmanagement.dto.request.StudentReqDTO;
import kr.co.gradesmanagement.dto.response.StudentResDTO;
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

    private final StudentRepository studentRepository;

    public void createStudent(StudentReqDTO.CREATE create) {
        final Student student = Student.create(create);
        checkStudentGradeExists(String.valueOf(create.getGrade()));
        checkStudentYearExists(create.getYear());
        studentRepository.save(student);
    }

    public List<StudentResDTO.READ> findAllStudents() {
        return studentRepository.findAllStudents().stream()
                .sorted(Comparator.comparing(Student::getGrade))
                .map(Student::read)
                .collect(Collectors.toList());
    }

    public List<StudentResDTO.READ> findStudentByGrade(Grade grade) {
        return studentRepository.findStudentByGrade(grade).stream()
                .map(Student::read)
                .collect(Collectors.toList());
    }

    private void checkStudentYearExists(int year) {
        if (year < 0 || year >= 6) {
            throw new IllegalArgumentException("⚠️[ERROR] " + year + "는 유효하지 않은 학년입니다. "
                    + "학년은 1~5학년 중에서 입력해주세요.");
        }
    }

    private void checkStudentGradeExists(String grade) {
        boolean isMatchGrade = Arrays.stream(Grade.values())
                .anyMatch(validGrade -> validGrade.name().equals(grade));

        if (!isMatchGrade) {
            throw new IllegalArgumentException("⚠️[ERROR] " + grade + "는 유효하지 않은 학점입니다. "
                    + "학점은 A, B, C, D, F 중에서 입력해주세요.");
        }
    }
}
