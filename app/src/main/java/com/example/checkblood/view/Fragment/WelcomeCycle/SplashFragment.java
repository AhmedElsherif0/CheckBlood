package com.example.checkblood.view.Fragment.WelcomeCycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.checkblood.R;

import static com.example.checkblood.helper.HelperMethod.ReplaceFragment;


public class SplashFragment extends Fragment {

    private static int SPLASH_TIME_OUT = 3000;

    public SplashFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            /*    if (LoadBoolean(getActivity(), REMEMBER) && loadUserData(getActivity()) != null) {

                    Intent i2 = new Intent(getActivity(), HomeActivity.class);
                    getActivity().startActivity(i2);
                    getActivity().finish();

                }else { */

            ReplaceFragment(getActivity().getSupportFragmentManager(),
                    new SlideFragment(), R.id.main_activity, null, null);

            }
        }, SPLASH_TIME_OUT);
          return view;
    }
}

