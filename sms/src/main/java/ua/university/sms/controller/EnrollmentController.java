package ua.university.sms.controller;

import org.springframework.web.bind.annotation.*;
import ua.university.sms.model.dto.EnrollmentRequest;
import ua.university.sms.model.dto.EnrollmentResponse;
import ua.university.sms.service.EnrollmentService;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public EnrollmentResponse create(@RequestBody EnrollmentRequest request) {
        return enrollmentService.create(request);
    }

    @GetMapping
    public List<EnrollmentResponse> getAll() {
        return enrollmentService.getAll();
    }
    @GetMapping("/unpaid")
    public List<EnrollmentResponse> getUnpaid() {
        return enrollmentService.getUnpaid();
    }
    @GetMapping("/{id}")
    public EnrollmentResponse getById(@PathVariable Long id) {
        return enrollmentService.getById(id);
    }

    @PutMapping("/{id}")
    public EnrollmentResponse update(@PathVariable Long id,
                                     @RequestBody EnrollmentRequest request) {
        return enrollmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        enrollmentService.delete(id);
    }
}