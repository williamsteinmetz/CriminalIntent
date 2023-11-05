package com.steinmetz.msu.criminalintent


import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CrimeDetailFragmentTest {
    private lateinit var scenario: FragmentScenario<CrimeDetailFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(
            themeResId = R.style.Theme_CriminalIntent
        )
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testNewCrimeDetails() {
        val title = "Testing New Crime Details!! "
        onView(withId(R.id.crime_title)).perform(typeText(title))
        onView(withId(R.id.crime_solved)).check(matches(isNotChecked())).perform(click())
        onView(withId(R.id.crime_solved)).check(matches(isChecked())).perform(click())
    }
}