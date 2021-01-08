package com.tom.kotlin

import com.tom.Human
import com.tom.Person
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PersonTest {
    @Test
    fun humanBmiTest() {
        val human = Human("Hank", 66.5f, 1.7f)
        Assertions.assertEquals(66.5f / (1.7f * 1.7f), human.bmi())
    }

    @Test
    fun personBmiTest() {
        val person = Person( 66.5f, 1.7f)
        Assertions.assertEquals(66.5f / (1.7f * 1.7f), person.bmi())
    }
}