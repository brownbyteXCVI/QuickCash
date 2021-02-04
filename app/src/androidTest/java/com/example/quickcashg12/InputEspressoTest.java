package com.example.quickcashg12;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.TypeTextAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class InputEspressoTest {
    @Rule
    public ActivityScenarioRule<Signup_pg> activityScenarioRule
            = new ActivityScenarioRule<>(Signup_pg.class);

    @Test
    public void checkInvalidUsername() {
        onView(withId(R.id.signupname))
                .perform(click())
                .perform(typeText(""));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupemail))
                .perform(click())
                .perform(typeText("example@email.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.employee))
                .perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupPass))
                .perform(click())
                .perform(typeText("Pass123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupFinish))
                .perform((click()));
        onView(withId(R.id.signupname))
                .check(matches(hasErrorText("Full name is required!")));
    }

    @Test
    public void checkInvalidEmail() {
        onView(withId(R.id.signupname))
                .perform(click())
                .perform(typeText("My Name"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupemail))
                .perform(click())
                .perform(typeText("example"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.employee))
                .perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupPass))
                .perform(click())
                .perform(typeText("Pass123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupFinish))
                .perform((click()));
        onView(withId(R.id.signupemail))
                .check(matches(hasErrorText("Input a valid email address!")));
    }

}
