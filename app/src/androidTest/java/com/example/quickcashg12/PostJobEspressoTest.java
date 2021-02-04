package com.example.quickcashg12;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.core.AllOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class PostJobEspressoTest {
    @Rule
    public ActivityScenarioRule<PostJob> activityScenarioRule
            = new ActivityScenarioRule<>(PostJob.class);

    @Test
    public void jobPostTest() {
        onView(withId(R.id.jobTitleEditText))
                .perform(click())
                .perform(typeText("Teacher"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jobLocationEditText))
                .perform(click())
                .perform(typeText("Canada"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.jobTypeDropDown))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(JobType.EDUCATION.toString())))
                .perform(click());

        onView(withId(R.id.salaryEditText))
                .perform(click())
                .perform(typeText("70000"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.postJobButton)).perform(click());

        onView(withId(R.id.jobPostStatus)).check(matches(isDisplayed()));
    }

    @Test
    public void invalidJobTitleTest() {
        onView(withId(R.id.jobTitleEditText))
                .perform(click())
                .perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jobLocationEditText))
                .perform(click())
                .perform(typeText("Canada"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.jobTypeDropDown))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(JobType.EDUCATION.toString())))
                .perform(click());

        onView(withId(R.id.salaryEditText))
                .perform(click())
                .perform(typeText("70000"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.postJobButton)).perform(click());
        onView(withId(R.id.jobTitleEditText))
                .check(matches(hasErrorText("Please enter a valid job name")));
    }

    @Test
    public void invalidJobLocationTest() {
        onView(withId(R.id.jobTitleEditText))
                .perform(click())
                .perform(typeText("Teacher"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jobLocationEditText))
                .perform(click())
                .perform(typeText("Canada_1"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.jobTypeDropDown))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(JobType.EDUCATION.toString())))
                .perform(click());

        onView(withId(R.id.salaryEditText))
                .perform(click())
                .perform(typeText("70000"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.postJobButton)).perform(click());

        onView(withId(R.id.jobLocationEditText))
                .check(matches(hasErrorText("Please enter a valid location")));
    }

    @Test
    public void invalidSalaryTest() {
        onView(withId(R.id.jobTitleEditText))
                .perform(click())
                .perform(typeText("Teacher"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.jobLocationEditText))
                .perform(click())
                .perform(typeText("Canada"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.jobTypeDropDown))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is(JobType.EDUCATION.toString())))
                .perform(click());

        onView(withId(R.id.salaryEditText))
                .perform(click())
                .perform(typeText("70000a"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.postJobButton)).perform(click());

        onView(withId(R.id.salaryEditText))
                .check(matches(hasErrorText("Please enter a valid salary")));
    }
}
