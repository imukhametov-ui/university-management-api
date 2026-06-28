package ua.university.sms.model.dto;

public class EnrollmentRequest {

    private Long studentId;
    private Long courseId;
    private Double grade;
    private Boolean paid;
    private String semester;

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