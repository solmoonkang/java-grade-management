package kr.co.gradesmanagement.controller;

import kr.co.gradesmanagement.infra.model.ApiResponse;
import kr.co.gradesmanagement.infra.model.ErrorCode;
import kr.co.gradesmanagement.model.domain.Grade;
import kr.co.gradesmanagement.model.dto.request.StudentReqDTO;
import kr.co.gradesmanagement.model.dto.response.StudentResDTO;
import kr.co.gradesmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ApiResponse<Void> createStudent(@RequestBody @Validated StudentReqDTO.CREATE create) {
        studentService.createStudent(create);
        return ApiResponse.successMessage(ErrorCode.SUCCESS_CREATED);
    }

    @GetMapping
    public ApiResponse<List<StudentResDTO.READ>> findAllStudents() {
        return ApiResponse.successMessageWithData(ErrorCode.SUCCESS_EXECUTE, studentService.findAllStudents());
    }

    @GetMapping("/grade")
    public ApiResponse<List<StudentResDTO.READ>> findStudentByGrade(@RequestParam Grade grade) {
        return ApiResponse.successMessageWithData(ErrorCode.SUCCESS_EXECUTE, studentService.findStudentByGrade(grade));
    }
}
