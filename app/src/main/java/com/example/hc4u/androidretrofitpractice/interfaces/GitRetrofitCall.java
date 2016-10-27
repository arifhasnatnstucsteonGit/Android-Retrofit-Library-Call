package com.example.hc4u.androidretrofitpractice.interfaces;

import com.example.hc4u.androidretrofitpractice.models.GitModel;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by HC4U on 10/27/2016.
 */

public interface GitRetrofitCall {

    @GET("users/arifhasnatnstucsteonGit")
    Call<GitModel> getStudentDetails();
}
