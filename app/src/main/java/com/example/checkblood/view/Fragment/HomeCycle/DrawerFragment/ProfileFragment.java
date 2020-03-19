package com.example.checkblood.view.Fragment.HomeCycle.DrawerFragment;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.checkblood.R;
import com.example.checkblood.data.api.Api;
import com.example.checkblood.data.api.ApiService;
import com.example.checkblood.view.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ProfileFragment extends BaseFragment {

    @BindView(R.id.profileFragment_tx_profile)
    TextView profileFragmentTxProfile;
    @BindView(R.id.profileFragment_et_name)
    EditText profileFragmentEtName;
    @BindView(R.id.profileFragment_et_email)
    EditText profileFragmentEtEmail;
    @BindView(R.id.profileFragment_tv_date)
    TextView profileFragmentTvDate;
    @BindView(R.id.profileFragment_sp_bloodType)
    Spinner profileFragmentSpBloodType;
    @BindView(R.id.profileFragment_tv_DonationDate)
    TextView profileFragmentTvDonationDate;
    @BindView(R.id.profileFragment_sp_government)
    Spinner profileFragmentSpGovernment;
    @BindView(R.id.profileFragment_sp_city)
    Spinner profileFragmentSpCity;
    @BindView(R.id.profileFragment_rl_city)
    RelativeLayout profileFragmentRlCity;
    @BindView(R.id.profileFragment_et_Phone)
    EditText profileFragmentEtPhone;
    @BindView(R.id.profileFragment_et_Password)
    EditText profileFragmentEtPassword;
    @BindView(R.id.profileFragment_et_confirmPassword)
    EditText profileFragmentEtConfirmPassword;
    @BindView(R.id.profileFragment_btn_Registration)
    Button profileFragmentBtnRegistration;
    private ApiService apiService;
    private Unbinder unbinder;


    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiService = Api.getClient().create(ApiService.class);

        return view;

    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();

    }

    @OnClick({R.id.profileFragment_tv_date, R.id.profileFragment_tv_DonationDate, R.id.profileFragment_btn_Registration})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.profileFragment_tv_date:
                break;
            case R.id.profileFragment_tv_DonationDate:
                break;
            case R.id.profileFragment_btn_Registration:
                editProfile();
                break;
        }
    }

    private void editProfile() {


        String name = profileFragmentEtName.getText().toString().trim();
        String email = profileFragmentEtEmail.getText().toString().trim();
        String birth_data = profileFragmentTvDate.getText().toString().trim();
        String donation_last_time = profileFragmentTvDonationDate.getText().toString().trim();
        String phone = profileFragmentEtPhone.getText().toString().trim();
        String password = profileFragmentEtPassword.getText().toString().trim();
        String confirmPassword = profileFragmentEtConfirmPassword.getText().toString().trim();


        if (name.isEmpty()) {
            profileFragmentEtName.setError("Name is Empty");
            profileFragmentEtName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            profileFragmentEtEmail.setError("Email is Required");
            profileFragmentEtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            profileFragmentEtEmail.setError("Email is Required");
            profileFragmentEtEmail.requestFocus();
            return;
        }


    }
}
