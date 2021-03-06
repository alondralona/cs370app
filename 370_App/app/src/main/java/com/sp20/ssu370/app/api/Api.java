package com.sp20.ssu370.app.api;

import com.sp20.ssu370.app.models.DefaultResponse;
import com.sp20.ssu370.app.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse>userLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
