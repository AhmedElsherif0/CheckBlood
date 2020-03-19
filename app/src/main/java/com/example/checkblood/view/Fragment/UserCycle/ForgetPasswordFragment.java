package com.example.checkblood.view.Fragment.UserCycle;


import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkblood.R;
import com.example.checkblood.data.api.Api;
import com.example.checkblood.data.api.ApiService;
import com.example.checkblood.data.model.changepassword.ChangePassword;
import com.example.checkblood.view.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.checkblood.helper.HelperMethod.ReplaceFragment;


public class ForgetPasswordFragment extends BaseFragment {


    @BindView(R.id.forgetpasswordfragment_et_phonenummber)
    EditText forgetpasswordfragmentEtPhonenummber;
    @BindView(R.id.forgetpasswordfragment_btn_send)
    Button forgetpasswordfragmentBtnSend;

    private ApiService apiService;
    private Unbinder unbinder;


    public ForgetPasswordFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forgetpassword, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiService = Api.getClient().create(ApiService.class);

        initFragment();

        return view;
    }

    private void ChangePassword() {


        String phone = forgetpasswordfragmentEtPhonenummber.getText().toString().trim();


        if (phone.isEmpty()) {
            forgetpasswordfragmentEtPhonenummber.setError("Phone is Empty");
            forgetpasswordfragmentEtPhonenummber.requestFocus();
        }
        if (phone.length() != 11) {
            forgetpasswordfragmentEtPhonenummber.setError("Phone Must Be 11 number");
            forgetpasswordfragmentEtPhonenummber.requestFocus();
        }
        if (!Patterns.PHONE.matcher(phone).matches()) {
            forgetpasswordfragmentEtPhonenummber.setError("Phone Not Match");
            forgetpasswordfragmentEtPhonenummber.requestFocus();
        }

        apiService.resetpassword(phone).enqueue(new Callback<ChangePassword>() {
            @Override
            public void onResponse(Call<ChangePassword> call, Response<ChangePassword> response) {

                try {
                    if (response.body().getStatus() == 1) {

                     //   Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                        NewPasswordFragment newPasswordFragment = new NewPasswordFragment();
                        newPasswordFragment.phone = phone;


                        ReplaceFragment(getActivity().getSupportFragmentManager(),
                                newPasswordFragment, R.id.user_activity, null, null);

                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();}

                } catch (Exception e) { e.printStackTrace();}
            }

            @Override
            public void onFailure(Call<ChangePassword> call, Throwable t) {
                Log.d("", "onFailure", t.getCause());
            }
        });
    }

        @Override
        public void onBack() {
            baseActivity.superBackPressed();
        }

        @OnClick(R.id.forgetpasswordfragment_btn_send)
        public void onViewClicked() {

            ChangePassword();
        }
}
