package com.w3engineers.testkt.uitest

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.w3engineers.testkt.R
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiTestActivityTest {

    private lateinit var targetedText: String

    @get:Rule
    var activityRule: ActivityTestRule<UiTestActivity> =
        ActivityTestRule<UiTestActivity>(UiTestActivity::class.java)

    @Before
    fun setup() {
        targetedText = "Khulna"
    }

    @Test
    fun spinnerTest() {
        addDelay()

        onView(withId(R.id.spinner_item)).perform(click())

        addDelay()
        addDelay()

        // click on 2nd item

        onData(allOf(`is`(instanceOf(String::class.java)))).atPosition(1).perform(click())

        addDelay()
        addDelay()

        onView(withId(R.id.spinner_item)).check(matches(withSpinnerText(containsString(targetedText))))
    }


    fun addDelay() {
        Thread.sleep(500)
    }
}

