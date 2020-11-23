package com.example.asgamesnake;

public class SettingsSingleton {

    private static SettingsSingleton staticInstance;
    private boolean isLightTheme=false;

    private SettingsSingleton()
    {

    }

    public static SettingsSingleton getInstance()
    {
        if(staticInstance==null)
        {
            staticInstance=new SettingsSingleton();
        }
       return staticInstance;
    }

    public boolean isLightTheme() {
        return isLightTheme;
    }

    public void setLightTheme(boolean lightTheme) {
        isLightTheme = lightTheme;
    }
}
