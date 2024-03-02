package kr.co.gradesmanagement.repository;

import kr.co.gradesmanagement.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {

    private static final Map<Long, Student> store = new ConcurrentHashMap<>();

    private static final AtomicLong sequence = new AtomicLong();

    public void save(Student student) {
        store.put(student.getId(), generateIdIfNull(student));
    }

    private static Student generateIdIfNull(Student student) {
        if (student.getId() == null) {
            student = student.toBuilder()
                    .id(sequence.incrementAndGet())
                    .build();
        }
        return student;
    }
}
