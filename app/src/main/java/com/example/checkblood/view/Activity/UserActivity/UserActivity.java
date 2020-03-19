package com.example.checkblood.view.Activity.UserActivity;


import android.os.Bundle;
import com.example.checkblood.R;
import com.example.checkblood.view.Activity.BaseActivity;
import com.example.checkblood.view.Fragment.UserCycle.LoginFragment;
import static com.example.checkblood.helper.HelperMethod.ReplaceFragment;


public class UserActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        ReplaceFragment(getSupportFragmentManager(),new LoginFragment(),R.id.user_activity,null,null);


    }
}
