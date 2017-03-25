package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

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

		final AdView mAdView = (AdView) root.findViewById(R.id.adView);
		// Create an ad request. Check logcat output for the hashed device ID to
		// get test ads on a physical device. e.g.
		// "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
		final AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();
		mAdView.loadAd(adRequest);
		return root;
	}
}
