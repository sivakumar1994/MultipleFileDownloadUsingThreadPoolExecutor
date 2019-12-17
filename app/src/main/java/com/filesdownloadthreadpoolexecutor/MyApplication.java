package com.filesdownloadthreadpoolexecutor;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    /**
     * application instance
     */
    private static MyApplication myApplication;

    /**
     * activity reference count
     */
    private int activityReferences = 0;

    /**
     * if the same activity configurations changed
     */
    private boolean isActivityChangingConfigurations = false;

    /**
     * set the app instance
     *
     * @param myApplication application instance of this application
     */
    private static void setMyApplication(MyApplication myApplication) {
        myApplication = myApplication;
    }

    /**
     * this method returns the  application instance;
     *
     * @return application instance
     */
    public static MyApplication getMyAppInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setMyApplication(this);
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        //no implementation
    }

    @Override
    public void onActivityStarted(Activity activity) {
        activityReferences = activityReferences + 1;
        if (activityReferences == 1 && !isActivityChangingConfigurations) {
            //activity enters the fore ground state
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        //no implementation
    }

    @Override
    public void onActivityPaused(Activity activity) {
        //no implementation
    }

    @Override
    public void onActivityStopped(Activity activity) {
        isActivityChangingConfigurations = activity.isChangingConfigurations();
        activityReferences = activityReferences - 1;
        if (activityReferences == 0 && !isActivityChangingConfigurations) {
            //application  killed
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        //no implementation
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        //no implementation
    }
}
