package com.pgkk.di.module;

import android.app.Application;
import android.content.Context;

import com.pgkk.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

//    @Provides
//    @Singleton
//    RepoService provideRepoService(GithubRepoRetrofit retrofit) {
//        return retrofit.get().create(RepoService.class);
//    }
//
//    @Provides
//    @Singleton
//    TrendingService provideTrendingService(GithubTrendingRetrofit retrofit) {
//        return retrofit.get().create(TrendingService.class);
//    }

}
