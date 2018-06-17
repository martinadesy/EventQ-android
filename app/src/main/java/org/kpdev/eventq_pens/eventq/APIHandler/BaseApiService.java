package org.kpdev.eventq_pens.eventq.APIHandler;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.Call;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login/")
    Call<ResponseBody> loginRequest(@Field("username") String username, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("registration/")
    Call<ResponseBody> registerRequest(@Field("username") String username, @Field("email") String email, @Field("password1") String password1, @Field("password2") String password2);

}
