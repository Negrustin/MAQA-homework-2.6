package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.annotation.ContentView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
public class IdlingTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before

    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdLingResources.idlingResource);
    }

    @After

    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdLingResources.idlingResource);
    }

    @Test

    public void navigationToGalleryOpensCorrectItemTest ()  {
        ViewInteraction appCompatImageButton = onView(
                withContentDescription("Open navigation drawer")
        );
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction gallery = onView(allOf(withId(R.id.nav_gallery)));
        gallery.check(matches((isDisplayed())));
        gallery.perform(click());

        ViewInteraction itemSeven = onView(allOf(withId(R.id.item_number), withText("7")));

        itemSeven.check(matches(withText("7")));
    }



}
