package com.mrchef.network;

import com.mrchef.food_detail.models.CatererDetailsAndMenu;
import com.mrchef.food_detail.models.SubmitMenuDetail;
import com.mrchef.login.User;
import com.mrchef.model.consumption_model.FoodConsumptionDetail;

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

  @GET("menuItems")
  Call<CatererDetailsAndMenu> menuRequest(@Query("date") String date);

  @GET("/submit/menu")
  Call<Boolean> submitMenu(@Body SubmitMenuDetail submitMenuDetail);

  @PUT("addItems")
  Call<Boolean> addFoodItems(@Body CatererDetailsAndMenu catererDetailsAndMenu);
}
