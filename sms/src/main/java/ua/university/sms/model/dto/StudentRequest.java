package ua.university.sms.model.dto;

public class StudentRequest {

    private String fullName;
    private String email;
    private Integer year;
    private String status;

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