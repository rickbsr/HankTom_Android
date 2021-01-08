package com.tom;

import com.kotlin.Student;

import java.util.Scanner;

public class Hello {
    public static void main(String[] args) {

//        Student stu = new Student("Hank", 50, 60); // for java

//        String s = new String("abcde");
        String s = null; // "abcde";
        System.out.println(s.charAt(3));
        System.out.println(s.length()); // 取長度
        System.out.println(s.substring(1, 4)); // 取子字串

        Person p = new Person("Tom", 66.5f, 1.7f);
        p.hello();
        p.weight = 66.5f;
        p.height = 1.7f;
        System.out.println(p.bmi());
        int score = 88;
        System.out.println(score > 80 || score < 90);
        char c = 'A';

        System.out.println(c > 'a');
        System.out.println("Please enter a number");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(n > 60);

        /*
        // 基本資料型態：共 8 種，基本資料型別是沒有功能的，但是都有對應的包覆型別
        // 整數型態
        int i = 0; // 32 bits
        Integer i2 = 0;

        short s = 24; // 16 bits

        long l = 1; // 64 bits

        char c = 'A'; // 16 bits，也可以儲存中文字
        Character c2 = '我';

        byte b = 120; // 8 bits

        // 小數型態：常數項預設為 double
        float f = 66.5f;
        Float f2 = 30.5f;

        double d = 1.7;
        Double d2 = 0.0;

        // 真假型態
        boolean bb = true;
        Boolean bb2 = false;

        // 參考資料型態
        String string = "Tom";
        */

        /*
        // 產生 Person 物件，並呼叫 hello
        new Person().hello();

        // 產生 Person 物件，並將之位址存入 p
        Person p = new Person();
        p.hello();
        */

        /*
        // 傳統的 Hello world
        System.out.println("Hello world");
        */
    }
}
