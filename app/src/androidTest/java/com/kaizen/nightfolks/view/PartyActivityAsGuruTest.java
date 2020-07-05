package com.kaizen.nightfolks.view;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.kaizen.nightfolks.R;
import com.kaizen.nightfolks.util.TestUtils;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.kaizen.nightfolks.util.TestUtils.atPosition;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class PartyActivityAsGuruTest {

    @BeforeClass
    // to disable bluetooth so that tests  work!Only a workaround  not the solution
    public static void setup() {
        ScanForPartiesActivity.setTest(true);
    }

    @Rule
    public ActivityTestRule<ScanForPartiesActivity> activityRule
            = new ActivityTestRule<>(ScanForPartiesActivity.class);


    @Test
    public void clickingJoinParty_shouldDisplayPartyActivity() {
        onView(ViewMatchers.withId(R.id.connectBtn)).perform(ViewActions.click());
        onView(withId(R.id.playlist_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void voteBtn_ShouldBeVisibleFromGuru() {
        //Accessing PartyActivity from ScanForActivity->Caller is Guru
        onView(withId(R.id.connectBtn)).perform(ViewActions.click());

        //minimum 3 elements are needed
        onView(withId(R.id.rvSongs))
                .check(matches(atPosition(0, isDisplayed())));
        onView(withId(R.id.rvSongs))
                .check(matches(atPosition(1, isDisplayed())));
        onView(withId(R.id.rvSongs))
                .check(matches(atPosition(2, isDisplayed())));
    }

    @Test
    public void voting_shouldBeAllowedOnlyOncePerSong() {
        onView(withId(R.id.connectBtn)).perform(ViewActions.click());
        onView(withId(R.id.rvSongs)).perform(
                RecyclerViewActions.actionOnItemAtPosition(1, TestUtils.clickChildViewWithId(R.id.voteBtn)));
        onView(withId(R.id.rvSongs))
                .check(matches(atPosition(1, not(isClickable()))));
    }

    @Test
    public void historyBtn_shouldNotBeVisibleFromGuru() {
        onView(withId(R.id.connectBtn)).perform(ViewActions.click());
        onView(withId(R.id.historyBtn)).check(matches(not(isDisplayed())));
    }
}