package com.example.checkblood.view.Fragment.HomeCycle.HomeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.checkblood.R;
import com.example.checkblood.view.Fragment.BaseFragment;


public class DonationRequestFragment extends BaseFragment {


    public DonationRequestFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donationrequest, container, false);

        return view;
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();

    }
}
