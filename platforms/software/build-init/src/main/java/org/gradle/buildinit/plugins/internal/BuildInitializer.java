/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.buildinit.plugins.internal;

import org.gradle.buildinit.plugins.internal.modifiers.BuildInitDsl;
import org.gradle.buildinit.plugins.internal.modifiers.BuildInitTestFramework;
import org.gradle.buildinit.plugins.internal.modifiers.ModularizationOption;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Initializes a Gradle build, either by converting an existing build to Gradle or generating a new Gradle build.
 *
 * <p>This interface currently includes a bunch of methods that are related to build generation and should move to {@link BuildGenerator}.</p>
 */
public interface BuildInitializer {
    /**
     * Unique and user-friendly ID for the type of the initialized build like 'java-application' or 'kotlin-library'.
     * <p>
     * Users can select the desired type by providing a value of a known ID on the command line.
     */
    String getId();

    boolean supportsJavaTargets();

    /**
     * Can this type of project be split-up into multiple subprojects?
     */
    Set<ModularizationOption> getModularizationOptions();

    /**
     * Returns the set of DSLs supported for this type of project, in display order.
     */
    Set<BuildInitDsl> getDsls();

    /**
     * The preferred DSL to use for this type of project.
     */
    BuildInitDsl getDefaultDsl();

    /**
     * Does a project name make sense for this type of project?
     */
    boolean supportsProjectName();

    /**
     * Does a source package name make sense for this type of project?
     */
    boolean supportsPackage();

    /**
     * Returns the set of test frameworks supported for this type of project.
     */
    Set<BuildInitTestFramework> getTestFrameworks(ModularizationOption modularizationOption);

    /**
     * Returns {@link BuildInitTestFramework#NONE} when no tests generated by default.
     */
    BuildInitTestFramework getDefaultTestFramework(ModularizationOption modularizationOption);

    List<String> getDefaultProjectNames();

    /**
     * Returns a collection of further reading related to this type of build (may be empty).
     */
    Optional<String> getFurtherReading(InitSettings settings);

    /**
     * Generates content for the given build.
     */
    void generate(InitSettings settings);
}
