package com.mrchef.food_detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.mrchef.R;
import com.mrchef.adapter.FoodConsumptionListAdapter;
import com.mrchef.databinding.ActivityFoodSelectionBinding;
import com.mrchef.model.consumption_model.FoodConsumptionDetail;
import com.mrchef.model.consumption_model.MenuItem;
import com.mrchef.network.IMrChefApi;
import com.mrchef.widget.CustomProgressDialog;

import java.util.List;

import retrofit2.Retrofit;

public class FoodConsumptionDetailActivity extends AppCompatActivity {
  private ActivityFoodSelectionBinding activityFoodSelectionBinding;
  private FoodConsumptionDetail foodConsumptionDetail;
  private CustomProgressDialog customProgressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityFoodSelectionBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_food_selection);
    customProgressDialog = new CustomProgressDialog(this, 0, true);
    customProgressDialog.show();
    callApi();
  }

  private void callApi() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("").build(); // add the base Url
    IMrChefApi service = retrofit.create(IMrChefApi.class);
  }


  private void loadDataToRecyclerView(List<MenuItem> menuItemList) {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    activityFoodSelectionBinding.rvFoodList.setLayoutManager(linearLayoutManager);
    FoodConsumptionListAdapter foodListingAdapter =
        new FoodConsumptionListAdapter(menuItemList, this);
    activityFoodSelectionBinding.rvFoodList.setAdapter(foodListingAdapter);
  }
}
