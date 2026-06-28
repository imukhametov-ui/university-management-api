package ua.university.sms.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest {

    @Test
    void testAverageCalculation() {

        double gpa = (95 + 90 + 85) / 3.0;

        assertEquals(90.0, gpa);
    }
    @Test
    void testMaxGrade() {
        double grade = 100.0;

        assertEquals(100.0, grade);
    }

    @Test
    void testMinGrade() {
        double grade = 0.0;

        assertEquals(0.0, grade);
    }
}