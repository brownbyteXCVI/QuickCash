package com.example.quickcashg12;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PaypalEspressoTest {
    @Rule
    public ActivityScenarioRule<PayActivity> activityScenarioRule
            = new ActivityScenarioRule<>(PayActivity.class);

    @Test
    public void testWrongPayment() {
        onView(withId(R.id.edtAmount))
                .perform(click())
                .perform(typeText("-500"));
        Espresso.closeSoftKeyboard();;
        onView(withId(R.id.button))
                .perform(click());
    }

    @Test
    public void testPayment() {
        onView(withId(R.id.edtAmount))
                .perform(click())
                .perform(typeText("500"));
        Espresso.closeSoftKeyboard();;
        onView(withId(R.id.button))
                .perform(click());
    }



}
