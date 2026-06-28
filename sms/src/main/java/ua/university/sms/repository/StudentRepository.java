package ua.university.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.university.sms.model.entity.Student;
import java.util.List;
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStatus(String status);

    List<Student> findByYear(Integer year);

    List<Student> findByStatusAndYear(String status, Integer year);
    List<Student> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String fullName,
            String email
    );
}