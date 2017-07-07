package com.mrchef.network;

import com.mrchef.model.consumption_model.FoodConsumptionDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by vinod on 07/07/17.
 */

public interface IMrChefApi {
  // just an demo api do not use it
  @GET("users/{user}/repos")
  Call<FoodConsumptionDetail> listRepos(@Path("user") String user);
}
