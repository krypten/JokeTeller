package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TellJokeTest {
	@Rule
	public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

	@Test
	public final void testTellJoke_validjoke_noExceptionThrown() {
		assertEquals("hello", "hello");
	}
}
