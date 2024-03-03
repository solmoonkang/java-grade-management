package kr.co.gradesmanagement.service;

import kr.co.gradesmanagement.domain.Student;
import kr.co.gradesmanagement.dto.request.StudentReqDTO;
import kr.co.gradesmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void createStudent(StudentReqDTO.CREATE create) {
        final Student student = Student.create(create);
        validateStudentYear(create.getYear());
        studentRepository.save(student);
    }

    private void validateStudentYear(int year) {
        if (year < 0 || year >= 6) {
            throw new IllegalArgumentException("⚠️[ERROR] " + year + "는 유효하지 않은 학년입니다. 학년은 1~5학년 중에서 입력해주세요.");
        }
    }
}
