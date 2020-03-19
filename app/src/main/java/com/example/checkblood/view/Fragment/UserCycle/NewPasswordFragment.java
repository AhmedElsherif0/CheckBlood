package com.example.checkblood.view.Fragment.UserCycle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.checkblood.R;
import com.example.checkblood.data.api.Api;
import com.example.checkblood.data.api.ApiService;
import com.example.checkblood.data.model.NewPassword.NewPassword;
import com.example.checkblood.view.Fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.checkblood.helper.HelperMethod.ReplaceFragment;


public class NewPasswordFragment extends BaseFragment {


    @BindView(R.id.newpasswordfragment_et_code)
    EditText newpasswordfragmentEtCode;
    @BindView(R.id.newpasswordfragment_et_newpassword)
    EditText newpasswordfragmentEtNewpassword;
    @BindView(R.id.newpasswordfragment_et_confirmdpassword)
    EditText newpasswordfragmentEtConfirmdpassword;
    @BindView(R.id.newpasswordfragment_btn_changepassword)
    Button newpasswordfragmentBtnChangepassword;


    private ApiService apiService;
    public String phone;
    private Unbinder unbinder;


    public NewPasswordFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_newpassword, container, false);

        unbinder = ButterKnife.bind(this, view);
        apiService = Api.getClient().create(ApiService.class);

        initFragment();

        return view;
    }

    @Override
    public void onBack() {
        baseActivity.superBackPressed();

    }


    public void onNewPassword() {

        String password = newpasswordfragmentEtNewpassword.getText().toString().trim();
        String password_confirmation = newpasswordfragmentEtConfirmdpassword.getText().toString().trim();
        String pin_code = newpasswordfragmentEtCode.getText().toString().trim();

        if (password.isEmpty()) {
            newpasswordfragmentEtNewpassword.setError("Password is Required");
            newpasswordfragmentEtNewpassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            newpasswordfragmentEtNewpassword.setError("Password Must Be 6 number");
            newpasswordfragmentEtNewpassword.requestFocus();
            return;
        }
        if (password_confirmation.isEmpty()) {
            newpasswordfragmentEtConfirmdpassword.setError("Confirm The Password");
            newpasswordfragmentEtConfirmdpassword.requestFocus();
            return;
        }
        if (!password_confirmation.equals(password)) {
            newpasswordfragmentEtConfirmdpassword.setError("Confirm NOT Match");
            newpasswordfragmentEtConfirmdpassword.requestFocus();
            return;
        }


        apiService.newPassword(phone,password, password_confirmation, pin_code).enqueue(new Callback<NewPassword>() {
            @Override
            public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {

                try {
                        if (response.body().getStatus() == 1) {

                       //     Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            ReplaceFragment(getActivity().getSupportFragmentManager(),
                            new LoginFragment(), R.id.user_activity, null, null);


                        } else {
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            @Override
            public void onFailure(Call<NewPassword> call, Throwable t) {

                  Log.d("", "onFailure", t.getCause());
            }
        });


    }

    @OnClick(R.id.newpasswordfragment_btn_changepassword)
    public void onViewClicked() {

        onNewPassword();
    }

}