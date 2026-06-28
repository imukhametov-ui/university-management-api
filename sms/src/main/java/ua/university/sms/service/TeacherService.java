package ua.university.sms.service;

import org.springframework.stereotype.Service;
import ua.university.sms.model.dto.TeacherRequest;
import ua.university.sms.model.dto.TeacherResponse;
import ua.university.sms.model.entity.Teacher;
import ua.university.sms.repository.TeacherRepository;
import ua.university.sms.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherResponse create(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setFullName(request.getFullName());
        teacher.setEmail(request.getEmail());
        teacher.setDepartment(request.getDepartment());

        Teacher saved = teacherRepository.save(teacher);

        return toResponse(saved);
    }

    public List<TeacherResponse> getAll() {
        return teacherRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TeacherResponse getById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        return toResponse(teacher);
    }

    public TeacherResponse update(Long id, TeacherRequest request) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        teacher.setFullName(request.getFullName());
        teacher.setEmail(request.getEmail());
        teacher.setDepartment(request.getDepartment());

        Teacher updated = teacherRepository.save(teacher);

        return toResponse(updated);
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }

    private TeacherResponse toResponse(Teacher teacher) {
        return new TeacherResponse(
                teacher.getId(),
                teacher.getFullName(),
                teacher.getEmail(),
                teacher.getDepartment()
        );
    }
}