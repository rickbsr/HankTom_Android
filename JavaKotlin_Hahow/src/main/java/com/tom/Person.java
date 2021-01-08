package com.tom;

// 如果沒有繼承類別，會繼承 Object 類別
public class Person {
    String name;
    float weight;
    float height;

    // java 預設建構子：若我們沒有寫建構子，則才會有預設建構子，其樣子類似下面
//    public Person() {
//        super(); // 上層類別
//    }

    // java 建構子，名稱跟類別一樣，不能有回傳值，建構子能有多個
    public Person() {
    }

    public Person(float weight, float height) {
        this.weight = weight;
        this.height = height;
    }

    public Person(String name, float weight, float height) {
        // 呼叫 this() 一定要建構子的第一行
        this(weight, height);
        this.name = name;
    }

    // hello 方法
    public void hello() {
        System.out.println("Hello world");
    }

    public float bmi() {
        float bmi = weight / (height * height);
        return bmi;
    }
}
