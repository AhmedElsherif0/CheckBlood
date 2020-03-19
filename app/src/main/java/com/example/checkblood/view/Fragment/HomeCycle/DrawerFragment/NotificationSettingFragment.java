package com.example.checkblood.view.Fragment.HomeCycle.DrawerFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.checkblood.R;
import com.example.checkblood.view.Fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class NotificationSettingFragment extends BaseFragment {

    private Unbinder unbinder;


    public NotificationSettingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification_setting, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();

    }
}
