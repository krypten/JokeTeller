package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.androidlib.jokerandroidlib.JokeTellerActivity;
import com.udacity.gradle.backend.api.Api;

import java.io.IOException;

/**
 * Async Task to fetch the jokes from server.
 */
public class FetchJokeTask extends AsyncTask<Context, Void, String> {
	private static Api mJokeApiService = null;
	private View mProgressSpinner;
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
			Thread.sleep(1000);
			return mJokeApiService.getJoke().execute().getJoke();
		} catch (final IOException | InterruptedException e) {
			return e.getMessage();
		}
	}

	@Override
	protected void onPostExecute(final String joke) {
		final Intent intent = new Intent(mContext, JokeTellerActivity.class);
		intent.putExtra(JokeTellerActivity.JOKE_KEY, joke);
		mContext.startActivity(intent);
	}
}
