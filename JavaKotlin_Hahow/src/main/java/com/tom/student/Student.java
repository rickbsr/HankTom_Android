package com.tom.student;

public class Student {

    // 屬性，如果不給值，會自動初始化
    String id;
    String name;
    int english;
    int math;
    static int pass = 60;

    public Student(String name, int english, int math) {
        this.name = name;
        this.english = english;
        this.math = math;
    }

    // private 代表只能在該類別使用
    // protect 代表繼承也能用
    // package 預設
     int highest() {
        // 三元運算子
//        int max = (english > math) ? english : math;

        // if-else
        /*if (english > math) {
            max = english;
        } else {
            max = math;
        }*/
        return (english > math) ? english : math;
    }

    public void pirnt() {
        int average = getAverage();
        System.out.print(name + "\t" + english + "\t" + math +
                "\t" + getAverage() + "\t" +
                ((getAverage() >= pass) ? "PASS" : "FAILED"));
        char grading = 'F';

        switch (average / 10) {
            case 10:
            case 9:
                grading = 'A';
                break;
            case 8:
                grading = 'B';
                break;
            case 7:
                grading = 'C';
                break;
            case 6:
                grading = 'D';
                break;
            default:
                grading = 'F';
        }

        /*// 巢狀
        if (average >= 90 && average <= 100) {
            grading = 'A';
        } else if (average >= 80 && average <= 89) {
            grading = 'B';
        } else if (average >= 70 && average <= 79) {
            grading = 'C';
        } else if (average >= 60 && average <= 69) {
            grading = 'D';
        }*/

        System.out.println("\t" + grading);

        /*if (getAverage() >= 60) {
            System.out.println("\tPASS");
        } else {
            System.out.println("\tFAILED");
        }*/
    }

    public int getAverage() {
        return (english + math) / 2;
    }
}
