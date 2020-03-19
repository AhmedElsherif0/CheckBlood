package com.example.checkblood.view.Fragment.HomeCycle.DrawerFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.checkblood.R;
import com.example.checkblood.view.Fragment.BaseFragment;


public class RateAppFragment extends BaseFragment {


    public RateAppFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();

        return inflater.inflate(R.layout.fragment_rateapp, container, false);
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();

    }
}
