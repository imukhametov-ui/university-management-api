package ua.university.sms.model.dto;

public class StudentResponse {

    private Long id;
    private String fullName;
    private String email;
    private Integer year;
    private String status;

    public StudentResponse(Long id, String fullName, String email, Integer year, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.year = year;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getYear() {
        return year;
    }

    public String getStatus() {
        return status;
    }
}