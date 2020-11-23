package com.example.asgamesnake;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MenuActivity extends AppCompatActivity {

    private RelativeLayout mMainRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_placeholder, MainMenuFragment.newInstance());
        ft.commit();

        mMainRelativeLayout=findViewById(R.id.mainRelativeLayout);

    }

    public RelativeLayout getMainRelativeLayout() {
        return mMainRelativeLayout;
    }

    public void setMainRelativeLayout(RelativeLayout mMainRelativeLayout) {
        this.mMainRelativeLayout = mMainRelativeLayout;
    }
}