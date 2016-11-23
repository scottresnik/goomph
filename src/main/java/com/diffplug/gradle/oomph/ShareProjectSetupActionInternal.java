/*
 * Copyright 2016 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.gradle.oomph;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.team.core.RepositoryProvider;

import com.diffplug.common.base.Strings;
import com.diffplug.gradle.oomph.SetupAction.Internal;

public class ShareProjectSetupActionInternal extends Internal<ShareProjectSetupAction> {

	public ShareProjectSetupActionInternal(ShareProjectSetupAction host) {
		super(host);
	}

	@Override
	public void runWithinEclipse() throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		// add all projects to the workspace
		for (ProjectToImport projToImport : host.projects) {
			if (projToImport.isShare() && !Strings.isNullOrEmpty(projToImport.getProvider())) {
				Path path = new Path(projToImport.getProjectFile().toString());
				IProjectDescription description = workspace.loadProjectDescription(path);

				IProject project = workspace.getRoot().getProject(description.getName());
				RepositoryProvider.map(project, projToImport.getProvider());
			}
		}
	}
}
