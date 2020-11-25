package com.example.asgamesnake;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainMenuFragment extends Fragment {

    private Button mPlayButton;
    private Button mSettingsButton;
    private RelativeLayout mFragmentRelativeLayout;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    public static MainMenuFragment newInstance() {
        MainMenuFragment fragment = new MainMenuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main_menu, container, false);

        mFragmentRelativeLayout=view.findViewById(R.id.fragmentRelativeLayout);

        boolean isLightTheme=SettingsSingleton.getInstance(getActivity().getApplicationContext()).isLightTheme();

        mPlayButton =view.findViewById(R.id.bPlay);
        setPlayButtonOnClickListener(isLightTheme);

        mSettingsButton=view.findViewById(R.id.bSettings);
        setSettingsButtonOnClickListener();



        if(isLightTheme)
        {
            mFragmentRelativeLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
            mPlayButton.setTextColor(getResources().getColor(android.R.color.black));
            mSettingsButton.setTextColor(getResources().getColor(android.R.color.black));
        }
        else
        {
            mFragmentRelativeLayout.setBackgroundColor(getResources().getColor(android.R.color.black));
            mPlayButton.setTextColor(getResources().getColor(android.R.color.white));
            mSettingsButton.setTextColor(getResources().getColor(android.R.color.white));
        }

        return view;
    }

    public void setPlayButtonOnClickListener(final boolean isLightTheme)
    {
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MainActivity.class);
                intent.putExtra("isLightTheme",isLightTheme);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void setSettingsButtonOnClickListener()
    {
        mSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.exit_to_right);
                ft.replace(R.id.fragment_placeholder, SettingsMenuFragment.newInstance());
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
}