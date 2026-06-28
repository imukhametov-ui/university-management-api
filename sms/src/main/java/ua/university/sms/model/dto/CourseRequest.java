package ua.university.sms.model.dto;

public class CourseRequest {

    private String title;
    private Integer credits;
    private Long teacherId;

    public String getTitle() {
        return title;
    }

    public Integer getCredits() {
        return credits;
    }

    public Long getTeacherId() {
        return teacherId;
    }
}