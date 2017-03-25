package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class TellJokeTest {
	@Rule
	public ActivityTestRule<MainActivity> mActivityRule =
			new ActivityTestRule<>(MainActivity.class);

	@Test
	public void testTellJoke_nonEmptyJoke_noExceptionThrown() {
		onView(withId(R.id.tell_joke_btn)).perform(click());
		// This view is in a different Activity, no need to tell Espresso.
		onView(withId(R.id.joke_textview)).check(matches(withText()));
	}

	@Test
	public void testTellJoke_validjoke_noExceptionThrown() {
		(new TestAsyncTask()).execute(mActivityRule.getActivity());
	}

	private Matcher<? super View> withText() {
		return new TypeSafeMatcher<View>() {
			@Override
			public boolean matchesSafely(View view) {
				if (!(view instanceof TextView)) {
					return false;
				}
				return !TextUtils.isEmpty(((TextView) view).getText());
			}

			@Override
			public void describeTo(final Description description) {
			}
		};
	}

	class TestAsyncTask extends FetchJokeTask {
		@Override
		protected void onPostExecute(final String joke) {
			assertFalse(TextUtils.isEmpty(joke));
		}
	}
}
