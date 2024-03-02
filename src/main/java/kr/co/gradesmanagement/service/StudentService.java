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
        studentRepository.save(student);
    }
}
