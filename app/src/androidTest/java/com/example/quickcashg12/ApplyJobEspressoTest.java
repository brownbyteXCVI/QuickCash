package com.example.quickcashg12;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ApplyJobEspressoTest {

    @Rule
    public ActivityScenarioRule<apply_to_jobs> activityScenarioRule
            = new ActivityScenarioRule<>(apply_to_jobs.class);

    @Test
    public void applyJobTest() {
        onView(withId(R.id.employee_name))
                .perform(click())
                .perform(typeText("Test User"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.employee_email))
                .perform(click())
                .perform(typeText("test007@test.com"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_address))
                .perform(click())
                .perform(typeText("4, Private Drive"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_description))
                .perform(click())
                .perform(typeText("Test description for espresso test purpose"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.apply_button)).perform(click());

        onView(withId(R.id.applied_status))
                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidNameTest() {
        onView(withId(R.id.employee_name))
                .perform(click())
                .perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.employee_email))
                .perform(click())
                .perform(typeText("test007@test.com"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_address))
                .perform(click())
                .perform(typeText("4, Private Drive"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_description))
                .perform(click())
                .perform(typeText("Test description for espresso test purpose"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.apply_button)).perform(click());

        onView(withId(R.id.employee_name))
                .check(matches(hasErrorText("Please enter a valid name")));
    }

    @Test
    public void invalidEmailTest() {
        onView(withId(R.id.employee_name))
                .perform(click())
                .perform(typeText("Test user"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.employee_email))
                .perform(click())
                .perform(typeText("test007@test"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_address))
                .perform(click())
                .perform(typeText("4, Private Drive"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_description))
                .perform(click())
                .perform(typeText("Test description for espresso test purpose"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.apply_button)).perform(click());

        onView(withId(R.id.employee_email))
                .check(matches(hasErrorText("Please enter a valid email")));
    }

    @Test
    public void invalidAddressTest() {
        onView(withId(R.id.employee_name))
                .perform(click())
                .perform(typeText("Test User"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.employee_email))
                .perform(click())
                .perform(typeText("test007@test.com"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_address))
                .perform(click())
                .perform(typeText(""));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_description))
                .perform(click())
                .perform(typeText("Test description for espresso test purpose"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.apply_button)).perform(click());

        onView(withId(R.id.employee_address))
                .check(matches(hasErrorText("Please enter a valid address")));
    }

    @Test
    public void invalidDescriptionTest() {
        onView(withId(R.id.employee_name))
                .perform(click())
                .perform(typeText("Test User"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.employee_email))
                .perform(click())
                .perform(typeText("test007@test.com"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_address))
                .perform(click())
                .perform(typeText("4, Private Drive"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.employee_description))
                .perform(click())
                .perform(typeText(""));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.apply_button)).perform(click());

        onView(withId(R.id.employee_description))
                .check(matches(hasErrorText("Please enter a valid description")));
    }
}
