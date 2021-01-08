package com.codingbydumbbell.shop

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)

    @Test
    fun guessWrong() {
        val resources = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secretNumber.secret
        for (n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.number)).perform(clearText())
                onView(withId(R.id.number)).perform(typeText(n.toString()))
                onView(withId(R.id.button)).perform(click())
                val message =
                    if (n < secret) resources.getString(R.string.bigger)
                    else resources.getString(R.string.smaller)
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }
        }
    }

    @Test
    fun resetGame() { // 作業 1 - 重玩按鈕的測試
        val resources = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secretNumber.secret
        if (5 != secret) {
            onView(withId(R.id.number)).perform(clearText())
            onView(withId(R.id.number)).perform(typeText("5"))
            onView(withId(R.id.button)).perform(click())
            val message =
                if (5 < secret) resources.getString(R.string.bigger)
                else resources.getString(R.string.smaller)
            onView(withText(resources.getString(R.string.ok))).perform(click())
            Espresso.pressBack()
        }
        onView(withId(R.id.fab)).perform(click())
        onView(withText(R.string.ok)).perform(click())
        onView(withId(R.id.counter)).check(matches(withText("0")))
    }
}