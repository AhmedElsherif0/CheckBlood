package com.example.checkblood.view.Fragment.HomeCycle.HomeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.checkblood.R;
import com.example.checkblood.data.api.Api;
import com.example.checkblood.data.api.ApiService;
import com.example.checkblood.helper.HelperMethod;
import com.example.checkblood.view.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class RequsetDonateFragment extends BaseFragment {



    @BindView(R.id.requestDonationFragment_iv_backhome)
    ImageView requestDonationFragmentIvBackhome;
    @BindView(R.id.requestDonationFragment_iv_notification)
    ImageView requestDonationFragmentIvNotification;
    @BindView(R.id.requestDonationFragment_et_name)
    EditText requestDonationFragmentEtName;
    @BindView(R.id.requestDonationFragment_et_age)
    EditText requestDonationFragmentEtAge;
    @BindView(R.id.requestDonationFragment_sp_BloodType)
    Spinner requestDonationFragmentSpBloodType;
    @BindView(R.id.requestDonationFragment_sp_bagsNumber)
    Spinner requestDonationFragmentSpBagsNumber;
    @BindView(R.id.requestDonationFragment_et_hospital_name)
    EditText requestDonationFragmentEtHospitalName;
    @BindView(R.id.requestDonationFragment_sp_government)
    Spinner requestDonationFragmentSpGovernment;
    @BindView(R.id.requestDonationFragment_sp_city)
    Spinner requestDonationFragmentSpCity;
    @BindView(R.id.registerFragment_rl_city)
    RelativeLayout registerFragmentRlCity;
    @BindView(R.id.requestDonationFragment_et_Phone)
    EditText requestDonationFragmentEtPhone;
    @BindView(R.id.requestDonationFragment_btn_sendRequest)
    Button requestDonationFragmentBtnSendRequest;

    private ApiService apiService;
    private Unbinder unbinder;


    public RequsetDonateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_requestdonation, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiService = Api.getClient().create(ApiService.class);

        return view;
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();
    }


    @OnClick({R.id.requestDonationFragment_iv_backhome, R.id.requestDonationFragment_iv_notification,
              R.id.requestDonationFragment_btn_sendRequest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.requestDonationFragment_iv_backhome:
                onBack();
                break;
            case R.id.requestDonationFragment_iv_notification:
                HelperMethod.ReplaceFragment(getActivity().getSupportFragmentManager(),
                        new AlertsFragment(),R.id.drawer_layout,null,null);
                break;
            case R.id.requestDonationFragment_btn_sendRequest:
                break;
        }
    }
}
