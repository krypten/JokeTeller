package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
	public MainActivityFragment() { /* no-op */ }

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
	                         final Bundle savedInstanceState) {
		final View root = inflater.inflate(R.layout.fragment_main, container, false);
		final View progressSpinner = root.findViewById(R.id.joke_fetch_progess);
		// Hide the spinner
		progressSpinner.setVisibility(View.GONE);
		// Launch Joke Activity to show the joke on button click
		root.findViewById(R.id.tell_joke_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				progressSpinner.setVisibility(View.VISIBLE);
				(new FetchJokeTask()).execute(getActivity());
			}
		});
		return root;
	}
}
