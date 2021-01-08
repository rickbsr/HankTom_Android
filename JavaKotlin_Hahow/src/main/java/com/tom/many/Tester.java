package com.tom.many;

import java.util.*;

public class Tester {
    public static void main(String[] args) {

        // Map 使用 key 和 value，key 不能重覆
        HashMap<String, String> stocks = new HashMap<>();
        stocks.put("2330", "台積電");
        stocks.put("2317", "鴻海");
        stocks.put("2330", "TSMC"); // 同樣 key 會覆蓋
        stocks.get("2330"); // 沒有 index，靠 key
        System.out.println(stocks);
        for (String key : stocks.keySet()) {
            System.out.println(stocks.get(key));
        }

//        set();
//        list();
//        arrayTest();
    }

    private static void set() {
        HashSet<Integer> set = new HashSet<>();
        set.add(6);
        set.add(7);
        set.add(1);
        set.add(8);
        set.add(7); // set 不允許重覆
//        set.get(); // // set 沒有 index 值，所以沒有 get()
        for (int n : set) {
            System.out.println(n);
        }
    }

    private static void list() {
        //        ArrayList list = new ArrayList();
        ArrayList<Integer> list = new ArrayList(); // 使用 <> 來限定放入類型
        list.add(5); // Integer
        list.add(3);
        list.add(1);
        list.add(7);
        list.add(1); // 可以一直加
//        list.add("abc");
//        list.add(true);
        System.out.println(list);
        int n1 = (int) list.get(0);
        int n2 = list.get(3); // 可以不用轉型
//        String s = (String) list.get(2);
        System.out.println(list.size()); // 得到長度
        List<Integer> scores = Arrays.asList(68, 88, 77, 99, 50); // 使用 Arrays
        for (int score : scores) {
            System.out.println(score);
        }
    }

    private static void arrayTest() {
        int[] numbers = new int[5]; // 同性質
        numbers[0] = 5;
        numbers[4] = 3;
//        numbers[5] = 7; // ArrayIndexOutOfBoundsException

        int[] sorces = {68, 88, 77, 99, 50};
        System.out.println(sorces);
        for (int i = 0; i < 5; i++) {
            System.out.println(sorces[i]);
        }
        for (int n : sorces) {
            System.out.println(n);
        }
    }
}
