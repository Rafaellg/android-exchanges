package com.rafaelguimas.exchange

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.rafaelguimas.exchange.ui.GraphActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GraphFragmentTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(GraphActivity::class.java)

    @Test
    fun pressOneYearPeriodButton() {
        onView(withId(R.id.btGraphOneYear)).perform(click())

        onView(withId(R.id.btGraphOneYear)).check(matches(withFontSize(36f)))
        onView(withId(R.id.btGraphSixMonths)).check(matches(withFontSize(28f)))
        onView(withId(R.id.btGraphThreeMonths)).check(matches(withFontSize(28f)))
        onView(withId(R.id.btGraphOneMonth)).check(matches(withFontSize(28f)))
    }

    @Test
    fun pressSixMonthsPeriodButton() {
        onView(withId(R.id.btGraphSixMonths)).perform(click())

        onView(withId(R.id.btGraphOneYear)).check(matches(withFontSize(28f)))
        onView(withId(R.id.btGraphSixMonths)).check(matches(withFontSize(36f)))
        onView(withId(R.id.btGraphThreeMonths)).check(matches(withFontSize(28f)))
        onView(withId(R.id.btGraphOneMonth)).check(matches(withFontSize(28f)))
    }

    @Test
    fun pressThreeMonthsPeriodButton() {
        onView(withId(R.id.btGraphThreeMonths)).perform(click())

        onView(withId(R.id.btGraphOneYear)).check(matches(withFontSize(28f)))
        onView(withId(R.id.btGraphSixMonths)).check(matches(withFontSize(28f)))
        onView(withId(R.id.btGraphThreeMonths)).check(matches(withFontSize(36f)))
        onView(withId(R.id.btGraphOneMonth)).check(matches(withFontSize(28f)))
    }

    @Test
    fun pressOneMonthPeriodButton() {
        onView(withId(R.id.btGraphOneMonth)).perform(click())

        onView(withId(R.id.btGraphOneYear)).check(matches(withFontSize(28f)))
        onView(withId(R.id.btGraphSixMonths)).check(matches(withFontSize(28f)))
        onView(withId(R.id.btGraphThreeMonths)).check(matches(withFontSize(28f)))
        onView(withId(R.id.btGraphOneMonth)).check(matches(withFontSize(36f)))
    }
}

class FontSizeMatcher(private val expectedSize: Float) : TypeSafeMatcher<View>(View::class.java) {

    override fun matchesSafely(target: View): Boolean {
        if (target !is TextView) {
            return false
        }
        return target.textSize == expectedSize
    }


    override fun describeTo(description: Description) {
        description.appendText("with fontSize: ")
        description.appendValue(expectedSize)
    }
}

fun withFontSize(fontSize: Float): Matcher<View> {
    return FontSizeMatcher(fontSize)
}
