package com.example.quickcashg12;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class CreditEspressoTest {

    @Rule
    public ActivityScenarioRule<Signup_pg> activityScenarioRule
            = new ActivityScenarioRule<>(Signup_pg.class);


    @Test
    public void checkInvalidCreditCardNo(){
        onView(withId(R.id.signupname))
                .perform(click())
                .perform(typeText("My Name"));
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
                .perform(typeText("password"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.creditNum))
                .perform(click())
                .perform(typeText("469378961238"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.PIN))
                .perform(click())
                .perform(typeText("7629"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.seucrityCode))
                .perform(click())
                .perform(typeText("789"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupFinish))
                .perform((click()));
        onView(withId(R.id.creditNum))
                .check(matches(hasErrorText("Invalid Credit Card Number! Enter a 16-digit number")));
    }

    @Test
    public void checkInvalidPIN(){
        onView(withId(R.id.signupname))
                .perform(click())
                .perform(typeText("My Name"));
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
                .perform(typeText("password"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.creditNum))
                .perform(click())
                .perform(typeText("4693789612388723"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.PIN))
                .perform(click())
                .perform(typeText("3657868"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.seucrityCode))
                .perform(click())
                .perform(typeText("786"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupFinish))
                .perform((click()));
        onView(withId(R.id.PIN))
                .check(matches(hasErrorText("Invalid PIN! Enter a 4-digit or 6-digit number")));
    }

    @Test
    public void checkInvalidSecurityCode(){
        onView(withId(R.id.signupname))
                .perform(click())
                .perform(typeText("My Name"));
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
                .perform(typeText("password"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.creditNum))
                .perform(click())
                .perform(typeText("4693789612388723"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.PIN))
                .perform(click())
                .perform(typeText("7629"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.seucrityCode))
                .perform(click())
                .perform(typeText("111111"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.signupFinish))
                .perform((click()));
        onView(withId(R.id.seucrityCode))
                .check(matches(hasErrorText("Invalid Security Code! Enter a 3-digit number")));
    }



}