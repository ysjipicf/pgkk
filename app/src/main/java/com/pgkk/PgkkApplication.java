package com.pgkk;

import android.app.Application;
import android.content.Context;

import com.pgkk.common.utils.AppLog;
import com.pgkk.di.component.ApplicationComponent;
import com.pgkk.di.component.DaggerApplicationComponent;
import com.pgkk.di.module.ApplicationModule;


/**
 * Created by tanxueze on 2017/12/7.
 */

public class PgkkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppLog.init();
    }

    public static PgkkApplication get(Context context) {
        return (PgkkApplication) context.getApplicationContext();
    }


    ApplicationComponent mApplicationComponent;
    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
