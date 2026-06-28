package ua.university.sms.service;

import org.springframework.stereotype.Service;
import ua.university.sms.model.dto.EnrollmentRequest;
import ua.university.sms.model.dto.EnrollmentResponse;
import ua.university.sms.model.entity.Course;
import ua.university.sms.model.entity.Enrollment;
import ua.university.sms.model.entity.Student;
import ua.university.sms.repository.CourseRepository;
import ua.university.sms.repository.EnrollmentRepository;
import ua.university.sms.repository.StudentRepository;
import ua.university.sms.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public EnrollmentResponse create(EnrollmentRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade(request.getGrade());
        enrollment.setPaid(request.getPaid());
        enrollment.setSemester(request.getSemester());

        return toResponse(enrollmentRepository.save(enrollment));
    }

    public List<EnrollmentResponse> getAll() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public EnrollmentResponse getById(Long id) {
        return toResponse(
                enrollmentRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"))
        );
    }

    public EnrollmentResponse update(Long id, EnrollmentRequest request) {

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setGrade(request.getGrade());
        enrollment.setPaid(request.getPaid());
        enrollment.setSemester(request.getSemester());

        return toResponse(enrollmentRepository.save(enrollment));
    }

    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }
    public List<EnrollmentResponse> getUnpaid() {
        return enrollmentRepository.findByPaidFalse()
                .stream()
                .map(this::toResponse)
                .toList();
    }
    private EnrollmentResponse toResponse(Enrollment enrollment) {
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId(),
                enrollment.getGrade(),
                enrollment.getPaid(),
                enrollment.getSemester()
        );
    }
}