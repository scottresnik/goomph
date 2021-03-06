/*
 * Copyright 2019 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.gradle.p2;

import java.io.File;
import java.util.Arrays;

import org.gradle.api.Project;

import com.diffplug.common.swt.os.SwtPlatform;
import com.diffplug.gradle.FileMisc;
import com.diffplug.gradle.eclipserunner.EclipseApp;

/**
 * Models the FeaturesAndBundlesPublisher application ([eclipse docs](https://wiki.eclipse.org/Equinox/p2/Publisher#Features_And_Bundles_Publisher_Application)).
 */
public class FeaturesAndBundlesPublisher extends EclipseApp {
	/** Creates a FeaturesAndBundlesPublisher. */
	public FeaturesAndBundlesPublisher() {
		super("org.eclipse.equinox.p2.publisher.FeaturesAndBundlesPublisher");
		consolelog();
	}

	/** Sets the given file to be the source. */
	public void source(File file) {
		// note that source doesn't want the protocol prefix
		addArg("source", file.getAbsolutePath());
	}

	/** Sets the given location to be the target for metadata. */
	public void metadataRepository(File file) {
		addArg("metadataRepository", FileMisc.asUrl(file));
	}

	/** Adds the given location to be the target for artifacts. */
	public void artifactRepository(File file) {
		addArg("artifactRepository", FileMisc.asUrl(file));
	}

	/** Adds the given location to be the target for artifacts. */
	public void configs(SwtPlatform... platforms) {
		configs(Arrays.asList(platforms));
	}

	/** Adds the given location to be the target for artifacts. */
	public void configs(Iterable<SwtPlatform> platforms) {
		for (SwtPlatform platform : platforms) {
			addArg("configs", platform.toString());
		}
	}

	/** Compresses the output index. */
	public void compress() {
		addArg("compress");
	}

	/** Performs the publishing in-place - no need to specify {@link #metadataRepository(File)} or {@link #artifactRepository(File)}. */
	public void inplace() {
		addArg("inplace");
	}

	/** Signals that artifacts should be exported. */
	public void publishArtifacts() {
		addArg("publishArtifacts");
	}

	/** Marks that the destination repositories should be appended to. */
	public void append() {
		addArg("append");
	}

	/** Runs this application, downloading a small bootstrapper if necessary. */
	public void runUsingBootstrapper() throws Exception {
		runUsing(P2BootstrapInstallation.latest().outsideJvmRunner());
	}

	/** Runs this application, downloading a small bootstrapper if necessary. */
	public void runUsingBootstrapper(Project project) throws Exception {
		runUsing(P2BootstrapInstallation.latest().outsideJvmRunner(project));
	}
}
