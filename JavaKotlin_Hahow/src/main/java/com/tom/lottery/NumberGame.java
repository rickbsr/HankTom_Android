package com.tom.lottery;

import java.util.HashSet;
import java.util.Set;

// 抽象類別
public abstract class NumberGame {
    Set<Integer> numbers = new HashSet<>();
    int numberCount = 0;

    // 抽象方法
    public abstract void generate();

    public boolean validate() {
        if (numbers.size() == numberCount)
            return true;
        else return false;
    }
}
