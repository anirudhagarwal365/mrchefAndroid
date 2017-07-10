package com.mrchef.network;

import com.mrchef.caterer.CatererDetailsAndMenu;
import com.mrchef.login.User;
import com.mrchef.model.consumption_model.FoodConsumptionDetail;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by vinod on 07/07/17.
 */

public interface IMrChefApi {
  // just an demo api do not use it
  @PUT("addUser")
  Call<Boolean> loginRequest(@Body User user);

  @PUT("addItems")
  Call<Boolean> addFoodItems(@Body CatererDetailsAndMenu catererDetailsAndMenu);
}
