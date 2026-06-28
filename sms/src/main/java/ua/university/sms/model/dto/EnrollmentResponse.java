package ua.university.sms.model.dto;

public class EnrollmentResponse {

    private Long id;
    private Long studentId;
    private Long courseId;
    private Double grade;
    private Boolean paid;
    private String semester;

    public EnrollmentResponse(Long id,
                              Long studentId,
                              Long courseId,
                              Double grade,
                              Boolean paid,
                              String semester) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
        this.paid = paid;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Double getGrade() {
        return grade;
    }

    public Boolean getPaid() {
        return paid;
    }

    public String getSemester() {
        return semester;
    }
}