package kr.co.gradesmanagement.repository;

import kr.co.gradesmanagement.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {

    private static final Map<Long, Student> store = new ConcurrentHashMap<>();

    private static final AtomicLong sequence = new AtomicLong(0L);

    public Student save(Student student) {
        Long studentId = sequence.incrementAndGet();
        Student newStudent = Student.builder()
                .id(studentId)
                .name(student.getName())
                .grade(student.getGrade())
                .year(student.getYear())
                .build();

        store.put(studentId, newStudent);
        return newStudent;
    }

    public Student findStudentById(Long studentId) {
        return store.get(studentId);
    }

    public void clearStore() {
        store.clear();
    }
}
