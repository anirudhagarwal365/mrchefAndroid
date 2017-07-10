package com.mrchef.network;

import com.mrchef.caterer.CatererDetailsAndMenu;
import com.mrchef.login.User;
import com.mrchef.model.consumption_model.UserMenuReport;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vinod on 07/07/17.
 */

public interface IMrChefApi {
  // just an demo api do not use it
  @PUT("addUser")
  Call<Boolean> loginRequest(@Body User user);

    @GET("reportsByDate")
    Call<UserMenuReport> reportsByDate(@Query("date") String date);

  @PUT("addItems")
  Call<Boolean> addFoodItems(@Body CatererDetailsAndMenu catererDetailsAndMenu);
}
