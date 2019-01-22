package com.pgkk.di.component;

import android.app.Application;
import android.content.Context;


import com.pgkk.di.ApplicationContext;
import com.pgkk.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

//    RepoService repoService();
//
//    TrendingService trendingService();
}
