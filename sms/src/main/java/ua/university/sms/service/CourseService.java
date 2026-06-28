package ua.university.sms.service;

import org.springframework.stereotype.Service;
import ua.university.sms.model.dto.CourseRequest;
import ua.university.sms.model.dto.CourseResponse;
import ua.university.sms.model.entity.Course;
import ua.university.sms.model.entity.Teacher;
import ua.university.sms.repository.CourseRepository;
import ua.university.sms.repository.TeacherRepository;
import ua.university.sms.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository,
                         TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public CourseResponse create(CourseRequest request) {
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setCredits(request.getCredits());
        course.setTeacher(teacher);

        return toResponse(courseRepository.save(course));
    }

    public List<CourseResponse> getAll() {
        return courseRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public CourseResponse getById(Long id) {
        return toResponse(courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found")));
    }

    public CourseResponse update(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        course.setTitle(request.getTitle());
        course.setCredits(request.getCredits());
        course.setTeacher(teacher);

        return toResponse(courseRepository.save(course));
    }
    public List<CourseResponse> filter(Long teacherId, Integer credits) {
        List<Course> courses;

        if (teacherId != null && credits != null) {
            courses = courseRepository.findByTeacherIdAndCredits(teacherId, credits);
        } else if (teacherId != null) {
            courses = courseRepository.findByTeacherId(teacherId);
        } else if (credits != null) {
            courses = courseRepository.findByCredits(credits);
        } else {
            courses = courseRepository.findAll();
        }

        return courses.stream()
                .map(this::toResponse)
                .toList();
    }
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getCredits(),
                course.getTeacher().getId()
        );
    }
}