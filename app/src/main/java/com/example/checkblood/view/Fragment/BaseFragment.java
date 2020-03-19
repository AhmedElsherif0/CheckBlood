package com.example.checkblood.view.Fragment;

import com.example.checkblood.view.Activity.BaseActivity;

import androidx.fragment.app.Fragment;


public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;

    public void initFragment() {

        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;
    }

    public void onBack() {
        baseActivity.superBackPressed();

    }

}