package com.example.asgamesnake;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class SettingsMenuFragment extends Fragment {

    private Button mBackButton;
    private SwitchCompat mLightThemeSwitch;
    private MenuActivity mActivity;
    private RelativeLayout mFragmentRelativeLayout;

    public SettingsMenuFragment() {
        // Required empty public constructor
    }


    public static SettingsMenuFragment newInstance() {
        SettingsMenuFragment fragment = new SettingsMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=(MenuActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_settings_menu, container, false);

        mFragmentRelativeLayout=view.findViewById(R.id.fragmentRelativeLayout);

        mBackButton=view.findViewById(R.id.bBack);
        setBackButtonOnClickListener();

        boolean isLightTheme=SettingsSingleton.getInstance().isLightTheme();

        mLightThemeSwitch =view.findViewById(R.id.sbTheme);
        mLightThemeSwitch.setChecked(isLightTheme);
        setDarkThemeSwitchOnCheckedChangedListener();


        if(isLightTheme)
        {
            mFragmentRelativeLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
            mBackButton.setTextColor(getResources().getColor(android.R.color.black));
            mLightThemeSwitch.setTextColor(getResources().getColor(android.R.color.black));
        }
        else
        {
            mFragmentRelativeLayout.setBackgroundColor(getResources().getColor(android.R.color.black));
            mBackButton.setTextColor(getResources().getColor(android.R.color.white));
           mLightThemeSwitch.setTextColor(getResources().getColor(android.R.color.white));
        }

        return view;
    }

    public void setBackButtonOnClickListener()
    {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
    }

    public void setDarkThemeSwitchOnCheckedChangedListener()
    {
        mLightThemeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    SettingsSingleton.getInstance().setLightTheme(true);
//                   mActivity.getMainRelativeLayout().setBackgroundColor(getResources().getColor(android.R.color.white));
//                   mLightThemeSwitch.setTextColor(getResources().getColor(android.R.color.black));
//                   mBackButton.setTextColor(getResources().getColor(android.R.color.black));
                }
                else
                {
                    SettingsSingleton.getInstance().setLightTheme(false);
//                    mActivity.getMainRelativeLayout().setBackgroundColor(getResources().getColor(android.R.color.black));
//                    mLightThemeSwitch.setTextColor(getResources().getColor(android.R.color.white));
//                    mBackButton.setTextColor(getResources().getColor(android.R.color.white));

                }
            }
        });
    }
}