package com.udacity.gradle.androidlib.jokerandroidlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a joke.
 */
public class JokerTellerFragment extends Fragment {
	public JokerTellerFragment() { /* no-op */ }

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
	                         final Bundle savedInstanceState) {
		final View root = inflater.inflate(R.layout.fragment_joke_teller, container, false);
		final Intent intent = getActivity().getIntent();
		if (intent != null) {
			final String joke = intent.getStringExtra(JokeTellerActivity.JOKE_KEY);
			if (!TextUtils.isEmpty(joke)) {
				((TextView) root.findViewById(R.id.joke_textview)).setText(joke);
			}
		}
		return root;
	}
}
