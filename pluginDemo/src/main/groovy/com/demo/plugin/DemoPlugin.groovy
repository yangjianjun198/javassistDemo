package com.demo.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.demo.transform.CustomTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

class DemoPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        if (!project.android) {
            throw new IllegalStateException(
                'Must apply \'com.android.application\' or \'com.android.library\' first!');
        }
        System.out.println("DemoPlugin apply")

        if (project.plugins.hasPlugin(AppPlugin)) {
            System.out.println("DemoPlugin app apply")
            def android = project.extensions.getByType(AppExtension)
            android.registerTransform(
                new CustomTransform(project, false))
        } else if (project.plugins.hasPlugin(LibraryPlugin)) {
            System.out.println("packageName is Library")
            def android = project.extensions.findByType(LibraryExtension)
            android.registerTransform(new CustomTransform(project, true))
        } else {
            System.out.println("DemoPlugin other apply")
        }
    }
}