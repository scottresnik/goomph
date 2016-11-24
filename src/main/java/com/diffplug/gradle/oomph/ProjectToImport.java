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

import java.io.File;
import java.io.Serializable;

public class ProjectToImport implements Serializable, Comparable<ProjectToImport> {

	private static final long serialVersionUID = -8877647508659729204L;

	private File projectFile;
	private boolean share;
	private String provider;
	private String pathToRepository;

	public ProjectToImport() {}

	public ProjectToImport(File projectFile) {
		this.projectFile = projectFile;
	}

	public File getProjectFile() {
		return projectFile;
	}

	public void setProjectFile(File projectFile) {
		this.projectFile = projectFile;
	}

	public boolean isShare() {
		return share;
	}

	public void setShare(boolean share) {
		this.share = share;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getPathToRepository() {
		return pathToRepository;
	}

	public void setPathToRepository(String pathToRepository) {
		this.pathToRepository = pathToRepository;
	}

	public boolean canShare() {
		return isShare() && !(null == getProvider() || "".equals(getProvider().trim()));
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ProjectToImport o) {
		if (o == null) {
			return 1;
		} else {
			if (getProjectFile() == null) {
				if (o.getProjectFile() == null) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return getProjectFile().compareTo(o.getProjectFile());
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((projectFile == null) ? 0 : projectFile.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + (share ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectToImport other = (ProjectToImport) obj;
		if (projectFile == null) {
			if (other.projectFile != null)
				return false;
		} else if (!projectFile.equals(other.projectFile))
			return false;
		if (provider == null) {
			if (other.provider != null)
				return false;
		} else if (!provider.equals(other.provider))
			return false;
		if (share != other.share)
			return false;
		return true;
	}

}
