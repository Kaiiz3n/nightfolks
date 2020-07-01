package com.kaizen.nightfolks.util;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.BoundedMatcher;

import com.kaizen.nightfolks.controller.music.adapters.SongsAdapter;
import com.kaizen.nightfolks.controller.partyManager.PartyFactory;
import com.kaizen.nightfolks.model.database.PartyDao;
import com.kaizen.nightfolks.model.database.PartyDatabase;
import com.kaizen.nightfolks.model.entities.Party;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static org.hamcrest.Matchers.allOf;

public class TestUtils {
    public static Party createDummyParty() {
        return PartyFactory.createParty("Party1", "Type1", "Kaiz3n");
    }

    private static List<Party> createDummyParties(int nbOfParties) {
        List<Party> partyList = new ArrayList<>(nbOfParties);
        for (int i = 0; i < nbOfParties; i++) {
            partyList.add(PartyFactory.createParty("Party" + i, "Type" + i, "Kaiz3n"));
        }
        return partyList;
    }

    public static void populateDb(PartyDatabase db, int nbOfParties) {
        PartyDao dao = db.getPartyDao();
        List<Party> partyList = createDummyParties(nbOfParties);
        partyList.forEach(dao::insertParty);
    }

    public static boolean areDateClose(Date date1, Date date2) {
        return Math.abs(date1.getTime() - date2.getTime()) <= 1000;
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        checkNotNull(itemMatcher);
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

    public static ViewAction forceClick() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isClickable(), isEnabled(), isDisplayed());
            }

            @Override
            public String getDescription() {
                return "force click";
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.performClick(); // perform click without checking view coordinates.
                uiController.loopMainThreadUntilIdle();
            }
        };
    }
}
