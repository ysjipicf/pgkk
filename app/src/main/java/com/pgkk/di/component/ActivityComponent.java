package com.pgkk.di.component;

import android.app.Activity;

import com.pgkk.di.PerActivity;
import com.pgkk.di.module.ActivityModule;

import dagger.Component;

/**
 * 向应用程序中的所有Activity注入依赖关系。
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
