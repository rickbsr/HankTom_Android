package com.tom.guess;

import java.util.Random;
import java.util.Scanner;

public class GameFor {
    public static void main(String[] args) {

        /*// 用於知道次數
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum = sum + i;
        }
        for (int i = 5; i >= 1; i--) {
            System.out.println(i);
        }
        System.out.println(sum);*/
        int sectet = new Random().nextInt(10) + 1;
        System.out.println(sectet);
        Scanner scanner = new Scanner(System.in);
        for (int i = 1; i <= 5; i++) {
            System.out.println("Please enter a number(" + i + "/4):");

            int number = scanner.nextInt();
            System.out.println("第" + i + "次:" + number);

            if (number > sectet) {
                System.out.println("lower");
            } else if (number < sectet) {
                System.out.println("higher");
            } else {
                System.out.println("Great, the number is " + number);
                break;
            }

            /*if (number == -1) {
                break; // 中斷迴圈
            }*/
        }
        System.out.println("結束");
    }
}
