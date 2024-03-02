package kr.co.gradesmanagement.controller;

import kr.co.gradesmanagement.dto.request.StudentReqDTO;
import kr.co.gradesmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentReqDTO.CREATE create) {
        studentService.createStudent(create);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
}
