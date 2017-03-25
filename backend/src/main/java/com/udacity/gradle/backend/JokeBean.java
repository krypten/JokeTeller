package com.udacity.gradle.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class JokeBean {
	private String mJoke;

	public String getJoke() {
		return mJoke;
	}

	public void setJoke(final String data) {
		mJoke = data;
	}
}