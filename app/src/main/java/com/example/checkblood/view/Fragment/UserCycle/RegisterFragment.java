package com.example.checkblood.view.Fragment.UserCycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.checkblood.R;
import com.example.checkblood.adapter.CustomSpinnerAdapter;
import com.example.checkblood.data.api.Api;
import com.example.checkblood.data.api.ApiService;
import com.example.checkblood.data.local.DateModel;
import com.example.checkblood.data.model.Regstration.Register;
import com.example.checkblood.data.model.generalresponse.GeneralResponse;
import com.example.checkblood.data.model.generalresponse.GeneralResponseData;
import com.example.checkblood.view.Activity.HomeActivity.HomeActivity;
import com.example.checkblood.view.Fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.checkblood.helper.HelperMethod.showCalender;
import static com.example.checkblood.data.local.SharedPreferencesManger.REMEMBER;
import static com.example.checkblood.data.local.SharedPreferencesManger.USER_DATA;
import static com.example.checkblood.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.checkblood.data.local.SharedPreferencesManger.saveBoolean;
import static com.example.checkblood.data.local.SharedPreferencesManger.saveObject;
import static com.example.checkblood.data.local.SharedPreferencesManger.saveString;

public class RegisterFragment extends BaseFragment {


    @BindView(R.id.registerFragment_et_name)
    EditText registerFragmentEtName;
    @BindView(R.id.registerFragment_et_email)
    EditText registerFragmentEtEmail;
    @BindView(R.id.registerFragment_tv_date)
    TextView registerFragmentEtDate;
    @BindView(R.id.registerFragment_sp_BloodTybe)
    Spinner registerFragmentSpBloodTybe;
    @BindView(R.id.registerFragment_tv_DonationDate)
    TextView registerFragmentEtDonationDate;
    @BindView(R.id.registerFragment_sp_government)
    Spinner registerFragmentSpGovernment;
    @BindView(R.id.registerFragment_sp_city)
    Spinner registerFragmentSpCity;
    @BindView(R.id.registerFragment_et_Phone)
    EditText registerFragmentEtPhone;
    @BindView(R.id.registerFragment_et_Password)
    EditText registerFragmentEtPassword;
    @BindView(R.id.registerFragment_et_confirmPassword)
    EditText registerFragmentEtConfirmPassword;
    @BindView(R.id.registerFragment_btn_Registration)
    Button registerFragmentBtnRegistration;
    @BindView(R.id.registerFragment_rl_city)
    RelativeLayout registerFragmentRlCity;

    List<String> bloodtypename = new ArrayList<>();
    List<String> governoratename = new ArrayList<>();
    List<String> citiesename = new ArrayList<>();


    private CustomSpinnerAdapter govermentAdapter, bloodtypeAdapter, cityAdapter;
    private DateModel lasttime, birthday;
    private ApiService apiService;
    private Unbinder unbinder;

    public RegisterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        initFragment();

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiService = Api.getClient().create(ApiService.class);

        getBloodType();
        getGoverment();

        birthday = new DateModel("1", "1", "1970", "1970-01-01");
        lasttime = new DateModel("1", "1", "1970", "1970-01-01");

        return view;
    }

    private void getBloodType() {
        apiService.getBloodType().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                try {

                    if (response.isSuccessful()) {

                        response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.blood_type)));
                        bloodtypename = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            bloodtypename.add(response.body().getData().get(i).getName());
                        }

                        bloodtypeAdapter = new CustomSpinnerAdapter(getActivity(), R.layout.item_custom_adapter,
                                R.id.customSpinnerAdapter_tv_names, response.body().getData(), bloodtypename);

                        registerFragmentSpBloodTybe.setAdapter(bloodtypeAdapter);
                        registerFragmentSpBloodTybe.setSelection(0);

                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });
    }

    private void getGoverment() {

        apiService.getGovernorate().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                try {

                    if (response.body().getStatus() == 1) {
                        registerFragmentSpCity.setVisibility(View.VISIBLE);
                        response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.govermentname)));
                        governoratename = new ArrayList<>();

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            governoratename.add(response.body().getData().get(i).getName());
                        }

                        govermentAdapter = new CustomSpinnerAdapter(getActivity(), R.layout.item_custom_adapter,
                                R.id.customSpinnerAdapter_tv_names, response.body().getData(), governoratename);

                        registerFragmentSpGovernment.setAdapter(govermentAdapter);

                        registerFragmentSpGovernment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    getCites(govermentAdapter.selected_id);
                                } else {
                                    registerFragmentRlCity.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

                try {
                    Log.d("", "onFailure:" + t.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getCites(int selected_id) {
        {
            apiService.getCity(selected_id).enqueue(new Callback<GeneralResponse>() {
                @Override
                public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                    try {

                        if (response.body().getStatus() == 1) {


                            response.body().getData().add(0, new GeneralResponseData(0, getString(R.string.citiesename)));
                            citiesename = new ArrayList<>();

                            for (int i = 0; i < response.body().getData().size(); i++) {
                                citiesename.add(response.body().getData().get(i).getName());
                            }
                            cityAdapter = new CustomSpinnerAdapter(getActivity(), R.layout.item_custom_adapter,
                                    R.id.customSpinnerAdapter_tv_names, response.body().getData(), citiesename);

                            registerFragmentSpCity.setAdapter(cityAdapter);
                            registerFragmentRlCity.setVisibility(View.VISIBLE);

                        } else {
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GeneralResponse> call, Throwable t) {

                    try {
                        Log.d("", "onFailure:" + t.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();
    }

    @OnClick({R.id.registerFragment_tv_date, R.id.registerFragment_tv_DonationDate, R.id.registerFragment_btn_Registration})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.registerFragment_tv_date:
                showCalender(getActivity(), getString(R.string.birthday), registerFragmentEtDate, birthday);
                break;
            case R.id.registerFragment_tv_DonationDate:
                showCalender(getActivity(), getString(R.string.last_donation_date), registerFragmentEtDonationDate, lasttime);
                break;
            case R.id.registerFragment_btn_Registration:
                signup();
                break;
        }
    }

    private void signup() {

        String name = registerFragmentEtName.getText().toString().trim();
        String email = registerFragmentEtEmail.getText().toString().trim();
        String birth_data = registerFragmentEtDate.getText().toString().trim();
        String donation_last_time = registerFragmentEtDonationDate.getText().toString().trim();
        String phone = registerFragmentEtPhone.getText().toString().trim();
        String password = registerFragmentEtPassword.getText().toString().trim();
        String confirmPassword = registerFragmentEtConfirmPassword.getText().toString().trim();


        if (name.isEmpty()) {
            registerFragmentEtName.setError("Name is Empty");
            registerFragmentEtName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            registerFragmentEtEmail.setError("Email is Required");
            registerFragmentEtEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerFragmentEtEmail.setError("Email is Required");
            registerFragmentEtEmail.requestFocus();
            return;
        }
        if (birth_data.isEmpty()) {
            Toast.makeText(getActivity(), "Please Choose Your Date", Toast.LENGTH_LONG).show();
            return;
        }
        if (bloodtypeAdapter == null) {
            Toast.makeText(getActivity(), "Blood Type Required", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (bloodtypeAdapter.selected_id == 0) {
                Toast.makeText(getActivity(), "Blood Type Required", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (donation_last_time.isEmpty()) {
            Toast.makeText(getActivity(), "Please Choose Last Donation", Toast.LENGTH_LONG).show();
            return;
        }
        if (govermentAdapter == null) {
            Toast.makeText(getActivity(), "Goverment is Required", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (govermentAdapter.selected_id == 0) {
                Toast.makeText(getActivity(), "Goverment is Required", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (cityAdapter == null) {
            Toast.makeText(getActivity(), "City is Required", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (cityAdapter.selected_id == 0) {
                Toast.makeText(getActivity(), "City is Required", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (phone.isEmpty()) {
            registerFragmentEtPhone.setError("Phone is Empty");
            registerFragmentEtPhone.requestFocus();
            return;
        }
        if (phone.length() != 11) {
            registerFragmentEtPhone.setError("Phone Must Be 11 number");
            registerFragmentEtPhone.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            registerFragmentEtPassword.setError("Password is Required");
            registerFragmentEtPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            registerFragmentEtPassword.setError("Password Must Be 6 number");
            registerFragmentEtPassword.requestFocus();
            return;
        }
        if (confirmPassword.isEmpty()) {
            registerFragmentEtConfirmPassword.setError("Confirm The Password");
            registerFragmentEtConfirmPassword.requestFocus();
            return;
        }
        if (!confirmPassword.equals(password)) {
            registerFragmentEtConfirmPassword.setError("Password Not Right");
            registerFragmentEtConfirmPassword.requestFocus();
            return;
        }


        apiService.CreateNewAccount(name, email, birth_data, bloodtypeAdapter.selected_id, donation_last_time,
                cityAdapter.selected_id, phone, password, confirmPassword).enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        saveObject(getActivity(), USER_DATA, response.body().getData());
                        saveBoolean(getActivity(), REMEMBER, true);
                        saveString(getActivity(), USER_PASSWORD, password);

                        Intent i1 = new Intent(getActivity(), HomeActivity.class);
                        getActivity().startActivity(i1);
                        getActivity().finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
                try {
                    Log.d("", "onFailure", t.getCause());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
