package com.test

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class MenuActivityTest{
    @get:Rule
    var mActivityTestRule: ActivityTestRule<MenuActivity> = ActivityTestRule(MenuActivity::class.java)

    @Test
    fun activity1Test() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_settings)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.txt_theme))
            .check(ViewAssertions.matches(ViewMatchers.withText("Тёмная тема")))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.btn_new_game))
            .check(ViewAssertions.matches(ViewMatchers.withText("Новая игра")))
        Espresso.onView(ViewMatchers.withId(R.id.btn_new_game)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.txt_size))
            .check(ViewAssertions.matches(ViewMatchers.withText("Размер поля")))
        Espresso.onView(ViewMatchers.withId(R.id.et_size)).perform(ViewActions.replaceText("9"))
        Espresso.onView(ViewMatchers.withId(R.id.et_size))
            .check(ViewAssertions.matches(ViewMatchers.withText("9")))
        Espresso.pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.btn_new_game))
            .check(ViewAssertions.matches(ViewMatchers.withText("Новая игра")))
    }

    @Test
    fun activity1Test2() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_high_score_table)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.recycler_highscore))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}