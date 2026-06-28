package ua.university.sms.controller;

import org.springframework.web.bind.annotation.*;
import ua.university.sms.model.dto.CourseRequest;
import ua.university.sms.model.dto.CourseResponse;
import ua.university.sms.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponse create(@RequestBody CourseRequest request) {
        return courseService.create(request);
    }

    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id,
                                 @RequestBody CourseRequest request) {
        return courseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
    @GetMapping
    public List<CourseResponse> getAll(
            @RequestParam(required = false) Long teacherId,
            @RequestParam(required = false) Integer credits) {

        return courseService.filter(teacherId, credits);
    }
}