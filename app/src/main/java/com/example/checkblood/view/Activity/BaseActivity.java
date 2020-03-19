package com.example.checkblood.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.checkblood.view.Fragment.BaseFragment;

public class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;

    public void superBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
