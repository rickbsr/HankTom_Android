package com.tom.guess;

import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Random random = new Random();
        int secret = random.nextInt(10) + 1; // 產生 1 ~ 10
        System.out.println(secret);

        // 用於不確定次數
        int number = 0;
        while (number != secret) {
            System.out.print("Please enter a number: ");
            number = scanner.nextInt();
//            System.out.println(number);
            if (number < secret) {
                System.out.println("higher");
            } else if (number > secret) {
                System.out.println("lower");
            } else {
                System.out.println("Great, the number is " + number);
            }
        }

        // 至少執行一次
        int i = 0;
        do {
            System.out.println(i);
            i++; // i = i + 1
        } while (i < 3);
    }
}
