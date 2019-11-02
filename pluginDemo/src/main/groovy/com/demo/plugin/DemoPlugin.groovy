package com.demo.plugin

import com.demo.transform.CustomTransform
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.AppExtension

class DemoPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        if (!project.android) {
            throw new IllegalStateException(
                'Must apply \'com.android.application\' or \'com.android.library\' first!');
        }
        System.out.println("DemoPlugin apply")
        def android = project.extensions.getByType(AppExtension)
        //注册一个Transform
        def classTransform = new CustomTransform(project)
        android.registerTransform(classTransform)

    }
}