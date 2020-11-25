package com.example.asgamesnake;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsSingleton {

    private static SettingsSingleton staticInstance;
    private boolean isLightTheme=false;
    public static final String SETTINGS="settings";
    SharedPreferences settings=null;

    private SettingsSingleton()
    {

    }

    private SettingsSingleton(Context context)
    {
        settings=context.getSharedPreferences(SETTINGS,Context.MODE_PRIVATE);
        isLightTheme=settings.getBoolean("isLightTheme",false);
    }

    public static SettingsSingleton getInstance(Context context)
    {
        if(staticInstance==null)
        {
            staticInstance=new SettingsSingleton(context);
        }
       return staticInstance;
    }

    public boolean isLightTheme() {
        return isLightTheme;
    }

    public void setLightTheme(boolean lightTheme) {
        isLightTheme = lightTheme;
        SharedPreferences.Editor edit=settings.edit();
        edit.putBoolean("isLightTheme",isLightTheme);
        edit.apply();
    }
}
