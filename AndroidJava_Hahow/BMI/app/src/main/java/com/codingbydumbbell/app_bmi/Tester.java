package com.codingbydumbbell.app_bmi;

import com.codingbydumbbell.app_bmi.hello.Person;
import com.codingbydumbbell.app_bmi.hello.Student;

public class Tester {

    public static void main(String[] args) {
        Student stu = new Student("001", "Hank", 60, 80);
        Student stu1= new Student("004", "Eric", 35, 60);
        stu.print();
        stu1.print();

        /*stu.setId("001");
        stu.setName("Hank");
        stu.setMath(60);
        stu.setEnglish(80);*/

//        System.out.println("Hello world");

        /*Person person = new Person();
        person.hello();
        person.hello("Tom");
//        person.weight = 66;
        person.setWeight(66);
//        person.height = 1.7f;
        person.setHeight(1.7f);
        System.out.println(person.bmi());*/
    }
}
