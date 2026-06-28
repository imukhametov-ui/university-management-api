package ua.university.sms.model.dto;

public class CourseResponse {

    private Long id;
    private String title;
    private Integer credits;
    private Long teacherId;

    public CourseResponse(Long id,
                          String title,
                          Integer credits,
                          Long teacherId) {
        this.id = id;
        this.title = title;
        this.credits = credits;
        this.teacherId = teacherId;
    }

    public Long getId() {
        return id;
    }

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