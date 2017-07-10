package com.mrchef.food_detail;

import java.util.List;

import com.mrchef.R;
import com.mrchef.adapter.FoodListingAdapter;
import com.mrchef.databinding.ActivityFoodSelectionBinding;
import com.mrchef.food_detail.models.CatererDetailsAndMenu;
import com.mrchef.login.LoginActivity;
import com.mrchef.login.User;
import com.mrchef.model.food_listing_model.FoodMenu;
import com.mrchef.model.food_listing_model.MenuItem;
import com.mrchef.network.IMrChefApi;
import com.mrchef.network.NetworkUtils;
import com.mrchef.widget.CustomProgressDialog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FoodSelectionActivity extends AppCompatActivity {
  private ActivityFoodSelectionBinding activityFoodSelectionBinding;
  private FoodMenu foodMenu;
  private CustomProgressDialog customProgressDialog;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityFoodSelectionBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_food_selection);
    customProgressDialog = new CustomProgressDialog(this, 0, true);
    customProgressDialog.show();
    // callApi();
  }

  private void callApi() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("").build(); // add the base Url
    IMrChefApi service = retrofit.create(IMrChefApi.class);

    final ProgressDialog progressDialog =
            new ProgressDialog(FoodSelectionActivity.this, R.style.AppTheme_Dark_Dialog);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Chefs Brainstorming!! Deciding the menu!! Please wait...");
    progressDialog.show();

    IMrChefApi iMrChefApi = NetworkUtils.getClient().create(IMrChefApi.class);
    Call<CatererDetailsAndMenu> catererDetailsAndMenuCall =
            iMrChefApi.menuRequest("");
    catererDetailsAndMenuCall.enqueue(new Callback<CatererDetailsAndMenu>() {

      @Override
      public void onResponse(Call<CatererDetailsAndMenu> call, Response<CatererDetailsAndMenu> response) {

      }

      @Override
      public void onFailure(Call<CatererDetailsAndMenu> call, Throwable t) {

      }
    });
  }

  private void loadDataToRecyclerView(List<MenuItem> menuItemList) {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    activityFoodSelectionBinding.rvFoodList.setLayoutManager(linearLayoutManager);
    FoodListingAdapter foodListingAdapter = new FoodListingAdapter(menuItemList, this);
    activityFoodSelectionBinding.rvFoodList.setAdapter(foodListingAdapter);
  }
}
