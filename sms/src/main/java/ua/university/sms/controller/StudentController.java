package ua.university.sms.controller;

import org.springframework.web.bind.annotation.*;
import ua.university.sms.model.dto.StudentRequest;
import ua.university.sms.model.dto.StudentResponse;
import ua.university.sms.model.entity.Student;
import ua.university.sms.service.StudentService;

import java.util.List;

@RestController

@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request) {
        return studentService.create(request);
    }
    @GetMapping("/search")
    public List<StudentResponse> search(
            @RequestParam String query) {

        return studentService.search(query);
    }
    @GetMapping
    public List<StudentResponse> getAll(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer year) {

        return studentService.filter(status, year);
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id,
                          @RequestBody Student student) {
        return studentService.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
    @GetMapping("/{id}/gpa")
    public Double getGpa(@PathVariable Long id) {
        return studentService.calculateGpa(id);
    }
    @GetMapping("/top")
    public List<StudentResponse> getTopStudents(
            @RequestParam(defaultValue = "5") Integer limit) {

        return studentService.getTopStudents(limit);
    }
}