package io.box.doitua.com.boxesmanager;

import android.app.Application;
import android.content.res.Resources;

public class App extends Application {
    private Resources res;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        res = getResources();
    }

    public static App getInstance() {
        return instance;
    }

    /**
     * Simplifying of getting application resources
     * @return resources
     */
    public Resources getMResources() {
        return res;
    }
}

