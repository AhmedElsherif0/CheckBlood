package com.example.checkblood.data.api;


import com.example.checkblood.data.model.NewPassword.NewPassword;
import com.example.checkblood.data.model.Regstration.Client;
import com.example.checkblood.data.model.Regstration.Register;
import com.example.checkblood.data.model.changepassword.ChangePassword;
import com.example.checkblood.data.model.generalresponse.GeneralResponse;
import com.example.checkblood.data.model.getArticle.Data;
import com.example.checkblood.data.model.getArticle.Datum;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @GET("blood-types")
    Call<GeneralResponse> getBloodType();

    @GET("governorates")
    Call<GeneralResponse> getGovernorate();

    @GET("cities")
    Call<GeneralResponse> getCity(@Query("governorate_id") int governorate_id);

    @POST("v1/register")
    @FormUrlEncoded
    Call<Register> CreateNewAccount(@Field("name") String name,
                                    @Field("email") String email,
                                    @Field("birth_date") String birth_data,
                                    @Field("blood_type_id") int blood_tybe_id,
                                    @Field("donation_last_date") String donation_last_time,
                                    @Field("city_id") int city_id,
                                    @Field("phone") String phone,
                                    @Field("password") String password,
                                    @Field("password_confirmation") String confirm_password);

    @POST("login")
    @FormUrlEncoded
    Call<Client> SingIn(@Field("phone") String phone,
                        @Field("password") String password);

    @POST("reset-password")
    @FormUrlEncoded
    Call<ChangePassword> resetpassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> newPassword(@Field("phone") String phone,
                                  @Field("password") String password,
                                  @Field("password_confirmation") String password_confirmation,
                                  @Field("pin_code") String pin_code);

    @GET("posts")
    Call <List<Datum>> getArticle ();


}
