package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.androidlib.jokerandroidlib.JokeTellerActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.backend.api.Api;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
	public static Api mJokeApiService = null;

	public MainActivityFragment() { /* no-op */ }

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
	                         final Bundle savedInstanceState) {
		final View root = inflater.inflate(R.layout.fragment_main, container, false);
		// Launch Joke Activity to show the joke on button click
		root.findViewById(R.id.tell_joke_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
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

	private class FetchJokeTask extends AsyncTask<Context, Void, String> {
		private Context mContext;

		@Override
		protected String doInBackground(Context... params) {
			mContext = params[0];
			if (mJokeApiService == null) {
				final Api.Builder builder = new Api.Builder(AndroidHttp.newCompatibleTransport(),
						new AndroidJsonFactory(), null)
						.setRootUrl(mContext.getResources().getString(R.string.api_root_url))
						.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
							@Override
							public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
								// turn off compression when running against local dev-app-server
								abstractGoogleClientRequest.setDisableGZipContent(true);
							}
						});
				mJokeApiService = builder.build();
			}

			try {
				return mJokeApiService.getJoke().execute().getJoke();
			} catch (final IOException e) {
				return e.getMessage();
			}
		}

		@Override
		protected void onPostExecute(final String joke) {
			final Intent intent = new Intent(mContext, JokeTellerActivity.class);
			intent.putExtra(JokeTellerActivity.JOKE_KEY, joke);
			startActivity(intent);
		}
	}
}
