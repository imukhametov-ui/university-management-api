package ua.university.sms.service;

import org.springframework.stereotype.Service;
import ua.university.sms.model.dto.StudentRequest;
import ua.university.sms.model.dto.StudentResponse;
import ua.university.sms.model.entity.Student;
import ua.university.sms.repository.StudentRepository;
import ua.university.sms.exception.ResourceNotFoundException;
import ua.university.sms.model.entity.Enrollment;
import ua.university.sms.repository.EnrollmentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public StudentService(StudentRepository studentRepository,
                          EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<StudentResponse> filter(String status, Integer year) {
        List<Student> students;

        if (status != null && year != null) {
            students = studentRepository.findByStatusAndYear(status, year);
        } else if (status != null) {
            students = studentRepository.findByStatus(status);
        } else if (year != null) {
            students = studentRepository.findByYear(year);
        } else {
            students = studentRepository.findAll();
        }

        return students.stream()
                .map(this::toResponse)
                .toList();
    }
    public StudentResponse create(StudentRequest request) {

        Student student = new Student();
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setYear(request.getYear());
        student.setStatus(request.getStatus());

        Student saved = studentRepository.save(student);

        return new StudentResponse(
                saved.getId(),
                saved.getFullName(),
                saved.getEmail(),
                saved.getYear(),
                saved.getStatus()
        );
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public Student update(Long id, Student updatedStudent) {

        Student student = getById(id);

        student.setFullName(updatedStudent.getFullName());
        student.setEmail(updatedStudent.getEmail());
        student.setYear(updatedStudent.getYear());
        student.setStatus(updatedStudent.getStatus());

        return studentRepository.save(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
    private StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getFullName(),
                student.getEmail(),
                student.getYear(),
                student.getStatus()
        );
    }
    public List<StudentResponse> search(String query) {

        return studentRepository
                .findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query)
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public Double calculateGpa(Long studentId) {

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        if (enrollments.isEmpty()) {
            return 0.0;
        }

        return enrollments.stream()
                .mapToDouble(Enrollment::getGrade)
                .average()
                .orElse(0.0);
    }
    public List<StudentResponse> getTopStudents(Integer limit) {
        return studentRepository.findAll()
                .stream()
                .sorted((s1, s2) -> Double.compare(
                        calculateGpa(s2.getId()),
                        calculateGpa(s1.getId())
                ))
                .limit(limit)
                .map(this::toResponse)
                .toList();
    }
}