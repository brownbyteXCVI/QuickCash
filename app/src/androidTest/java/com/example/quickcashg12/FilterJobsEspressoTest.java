package com.example.quickcashg12;

import android.widget.NumberPicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

public class FilterJobsEspressoTest {
    @Rule
    public ActivityScenarioRule<JobListActivity> activityScenarioRule
            = new ActivityScenarioRule<>(JobListActivity.class);
    @Test
    public void FilterJobTest(){
        onView(withId(R.id.filterJobsButton))
                .perform(click());
        onView(withId(R.id.titleFilter))
                .perform(click());
        onView(withId(R.id.filterTermEV))
                .perform(click()).perform(typeText("Teacher"));
        onView(withId(R.id.finishButton))
                .perform(click());

        onView(withId(R.id.filterJobsButton))
                .perform(click());
        onView(withId(R.id.locationFilter))
                .perform(click());
        onView(withId(R.id.filterTermEV))
                .perform(click()).perform(typeText("Canada"));
        onView(withId(R.id.finishButton))
                .perform(click());


        onView(withId(R.id.filterJobsButton))
                .perform(click());
        onView(withId(R.id.noneFilter))
                .perform(click());
        onView(withId(R.id.finishButton))
                .perform(click());









    }
}
