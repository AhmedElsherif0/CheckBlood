package com.example.checkblood.view.Activity.WelcomeActivity;

import android.os.Bundle;
import com.example.checkblood.R;
import com.example.checkblood.view.Activity.BaseActivity;
import com.example.checkblood.view.Fragment.WelcomeCycle.SplashFragment;
import static com.example.checkblood.helper.HelperMethod.ReplaceFragment;



public class WelcomeScreen extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       ReplaceFragment(getSupportFragmentManager(),new SplashFragment(),R.id.main_activity,null,null );

    }

}
