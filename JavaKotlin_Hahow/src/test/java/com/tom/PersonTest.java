package com.tom;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

// 測試範例
public class PersonTest {
    @Test // 要加入這個標籤
    public void bmiTest() {
        Person person = new Person(66.5f, 1.7f);
        Assertions.assertEquals(66.5f / (1.7f * 1.7f), person.bmi());
    }

}
