package com.tom.student;

import java.util.Scanner;

public class SchoolRunner {
    // static 代表他是獨立的，屬於類別層級，與物件無關
    public static void main(String[] args) {
//        userInput();
        com.kotlin.Student.Companion.getPass(); // Java 去取得 Kotlin 類別層級的資料

        Student.pass = 50;
        Student stu = new Student("Hank", 97, 10);
        Student stu2 = new Student("Tom", 60, 40);
        Student stu3 = new Student("Jane", 30, 55);

        GraduateStudent gstu = new GraduateStudent("Jack", 55,65,60);
        gstu.pirnt();

        stu.pirnt();
        stu2.pirnt();
        stu3.pirnt();


//        Student stu = new Student("Hank", 77, 99);
//        stu.pirnt();
        System.out.println("High score: " + stu.highest());
    }

    private static void userInput() {
        System.out.print("Please enter student's name: ");
        Scanner scanner = new Scanner(System.in); // 需要 import
        String name = scanner.next();
        System.out.print("Please enter student's english: ");
        int english = scanner.nextInt();
        System.out.print("Please enter student's math: ");
        int math = scanner.nextInt();
        Student stu = new Student(name, english, math);
        stu.pirnt();
        System.out.println("High score: " + stu.highest());
    }
}
