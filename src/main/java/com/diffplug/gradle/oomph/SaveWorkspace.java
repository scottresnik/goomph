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
package com.diffplug.gradle.oomph;

/** Last plugin to run, saves the workspace. */
class SaveWorkspace extends SetupAction {
	private static final long serialVersionUID = -3084624702911429068L;

	public SaveWorkspace() {
		super("com.diffplug.gradle.oomph.SaveWorkspaceInternal");
	}

	@Override
	public String getDescription() {
		return "save workspace";
	}
}
