package com.tom;

import com.tom.student.Student;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class StudentTest {
    @Test
    public void highestScoreTest() {
        Student student = new Student("Hank", 60, 80);
        Assertions.assertEquals(80, student.highest());
    }

    @Test
    public void averageTest() {
        Student student = new Student("Hank", 60, 80);
        Assertions.assertEquals((60 + 80) / 2, student.getAverage());
    }
}
