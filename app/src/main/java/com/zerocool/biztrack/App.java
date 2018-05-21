package com.zerocool.biztrack;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.zerocool.biztrack.DatabaseHandler.DBHelper;
import com.zerocool.biztrack.DatabaseHandler.DatabaseManager;

public class App extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);
        //TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/your_font_file.ttf");

    }


    public static Context getContext(){
        return context;
    }

}

