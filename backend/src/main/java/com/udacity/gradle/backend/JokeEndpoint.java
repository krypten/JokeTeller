/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.gradle.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.javalib.jokes.Joker;

/**
 * An endpoint class we are exposing.
 */
@Api(
		name = "api",
		version = "v1",
		namespace = @ApiNamespace(
				ownerDomain = "backend.gradle.udacity.com",
				ownerName = "backend.gradle.udacity.com",
				packagePath = ""
		)
)
public class JokeEndpoint {
	@ApiMethod(name = "getJoke")
	public JokeBean getJoke() {
		final JokeBean response = new JokeBean();
		response.setJoke((new Joker()).getJoke());
		return response;
	}
}
