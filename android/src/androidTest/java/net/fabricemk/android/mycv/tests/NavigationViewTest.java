package net.fabricemk.android.mycv.tests;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.*;

import net.fabricemk.android.mycv.R;
import net.fabricemk.android.mycv.tests.extensions.matchers.ToolbarMatcher;
import net.fabricemk.android.mycv.ui.activities.MainActivity;

import org.junit.Before;

public class NavigationViewTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    public NavigationViewTest() {
        super(MainActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mActivity = getActivity();
    }

    /**
     * Test that clicking on a Navigation Drawer Item will open the correct fragment.
     */
    @SmallTest
    public void testNavigationViewItemClick() {
        openDrawer(R.id.drawer_layout);

        onView(withText(R.string.career)).perform(click());
        ToolbarMatcher.matchToolbarTitle(getActivity().getString(R.string.career));

        openDrawer(R.id.drawer_layout);
        onView(withText(R.string.skills)).perform(click());
        ToolbarMatcher.matchToolbarTitle(getActivity().getString(R.string.skills));

        openDrawer(R.id.drawer_layout);
        onView(withText(R.string.trips)).perform(click());
        ToolbarMatcher.matchToolbarTitle(getActivity().getString(R.string.trips));

        openDrawer(R.id.drawer_layout);
        onView(withText(R.string.about)).perform(click());
        ToolbarMatcher.matchToolbarTitle(getActivity().getString(R.string.about));
    }

    /**
     * Test opening the Navigation Drawer and pressing the back button.
     * It should stay in the same Fragment
     */
    @SmallTest
    public void testNavigationViewBackButton() {
        openDrawer(R.id.drawer_layout);
        onView(allOf(withId(R.id.title), instanceOf(TextView.class), withText(R.string.contact)))
                .check(matches(withText(R.string.contact)));
        pressBack();
        onView(allOf(withId(R.id.title), instanceOf(TextView.class), withText(R.string.contact)))
                .check(matches(withText(R.string.contact)));
    }

    /**
     * On certain activities, the navigation view should not be available
     */
    @SmallTest
    public void testNavigationViewNotAvailable() {
        openDrawer(R.id.drawer_layout);
        onView(withText(R.string.about)).perform(click());
        onView(withId(R.id.drawer_layout)).check(doesNotExist());
    }

}
