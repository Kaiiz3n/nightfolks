package com.kaizen.nightfolks.view;

import android.content.Intent;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.kaizen.nightfolks.R;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.kaizen.nightfolks.util.TestUtils.atPosition;
import static com.kaizen.nightfolks.util.TestUtils.forceClick;

@RunWith(AndroidJUnit4.class)
public class PartyActivityAsDjTest {
    @Rule
    public ActivityTestRule activityRule
            = new ActivityTestRule<PartyActivity>(PartyActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Intent i = new Intent();
            i.putExtra("caller", "dj");
            return i;
        }
    };

    @BeforeClass
    public static void setup() {
        DJNewPartyActivity.setTest(true);
    }

    @Test
    public void historyBtn_shouldBeVisible() {
        onView(withId(R.id.historyBtn)).check(matches(isDisplayed()));
        onView(withId(R.id.historyBtn)).check(matches(isClickable()));
    }

    @Test
    public void voting_shouldNotBePossibleAsDj() {
        onView(withId(R.id.rvSongs))
                .check(matches(atPosition(1, isDisplayed())));
        onView(withId(R.id.rvSongs))
                .check(matches(atPosition(2, isDisplayed())));
    }
}
