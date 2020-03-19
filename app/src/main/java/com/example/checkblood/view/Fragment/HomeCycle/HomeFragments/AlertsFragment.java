package com.example.checkblood.view.Fragment.HomeCycle.HomeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.checkblood.R;
import com.example.checkblood.view.Fragment.BaseFragment;


public class AlertsFragment extends BaseFragment {


    public AlertsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alerts, container, false);

        initFragment();
        return view;
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();

    }
}
