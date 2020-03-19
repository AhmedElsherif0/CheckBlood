package com.example.checkblood.view.Fragment.UserCycle;


import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkblood.R;
import com.example.checkblood.data.api.Api;
import com.example.checkblood.data.api.ApiService;

import com.example.checkblood.data.model.Regstration.Client;

import com.example.checkblood.data.local.SharedPreferencesManger;
import com.example.checkblood.view.Activity.HomeActivity.HomeActivity;
import com.example.checkblood.view.Fragment.BaseFragment;


import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.checkblood.helper.HelperMethod.ReplaceFragment;
import static com.example.checkblood.helper.HelperMethod.dismissProgressDialog;
import static com.example.checkblood.helper.HelperMethod.showProgressDialog;
import static com.example.checkblood.data.local.SharedPreferencesManger.USER_DATA;
import static com.example.checkblood.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.checkblood.data.local.SharedPreferencesManger.saveBoolean;
import static com.example.checkblood.data.local.SharedPreferencesManger.saveObject;
import static com.example.checkblood.data.local.SharedPreferencesManger.saveString;


public class LoginFragment extends BaseFragment {


    @BindView(R.id.loginFragment_et_phone)
    EditText loginfragmentEtPhone;
    @BindView(R.id.loginFragment_et_password)
    EditText loginfragmentEtPassword;
    @BindView(R.id.loginfragment_ch_RememberMe)
    CheckBox loginfragmentChRememberMe;
    @BindView(R.id.loginfragment_tv_Forgetpassword)
    TextView loginfragmentTvForgetpassword;
    @BindView(R.id.loginfragment_btn_login)
    Button loginfragmentBtnLogin;
    @BindView(R.id.loginfragment_btn_signup)
    Button loginfragmentBtnSignup;
    @BindView(R.id.logo)
    ImageView logo;
    @BindView(R.id.loginfragment_tv_Rememberme)
    TextView loginfragmentTvRememberme;
    @BindView(R.id.login)
    ConstraintLayout login;


    private ApiService apiService;
    private MediaRouteButton progressBar;
    private Unbinder unbinder;
    private String REMEMBER;

    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiService = Api.getClient().create(ApiService.class);
        initFragment();

        return view;
    }


    @Override
    public void onBack() {
        baseActivity.superBackPressed();
    }

    public void OnLogin() {

        String phone = loginfragmentEtPhone.getText().toString().trim();
        String password = loginfragmentEtPassword.getText().toString().trim();


        if (phone.isEmpty()) {
            loginfragmentEtPhone.setError("Phone Isn't Valid");
            loginfragmentEtPhone.requestFocus();
            return;
        }
        if (phone.length() != 11) {
            loginfragmentEtPhone.setError("Phone Must Be 11 number");
            loginfragmentEtPhone.requestFocus();
            return;
        }
        if (!Patterns.PHONE.matcher(phone).matches()) {
            loginfragmentEtPhone.setError("Invalid phone");
            loginfragmentEtPhone.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            loginfragmentEtPassword.setError("Password is Required");
            return;
        }
        if (password.length() < 6) {
            loginfragmentEtPassword.setError("Password Must Be 6 number");
            return;
        }


        showProgressDialog(getActivity(), "Sign in Now ");

        apiService.SingIn(phone, password).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {

                try {
                    if (response.isSuccessful()) {

                        saveObject(getActivity(), USER_DATA, response.body().getPhone());
                        saveObject(getActivity(), USER_DATA, response.body().getPhone());
                        saveBoolean(getActivity(), REMEMBER, loginfragmentChRememberMe.isChecked());
                        saveString(getActivity(), USER_PASSWORD, password);

                        Intent i1 = new Intent(getActivity(), HomeActivity.class);
                        getActivity().startActivity(i1);
                        getActivity().finish();

                        dismissProgressDialog();

                    } else {
                        dismissProgressDialog();
                        Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    dismissProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                dismissProgressDialog();
            }
        });
    }

    @OnClick({R.id.loginfragment_ch_RememberMe, R.id.loginfragment_tv_Forgetpassword,
            R.id.loginfragment_btn_login, R.id.loginfragment_btn_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginfragment_ch_RememberMe:
                SharedPreferencesManger.LoadBoolean(getActivity(), REMEMBER);
                break;
            case R.id.loginfragment_tv_Forgetpassword:
                ReplaceFragment(getActivity().getSupportFragmentManager(), new ForgetPasswordFragment(),
                        R.id.user_activity, null, null);
                break;
            case R.id.loginfragment_btn_login:
                OnLogin();
                break;
            case R.id.loginfragment_btn_signup:
                ReplaceFragment(getActivity().getSupportFragmentManager(), new RegisterFragment(),
                        R.id.user_activity, null, null);
                break;
        }
    }
}
