package com.mrchef.food_detail;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.DatePicker;

import com.mrchef.R;
import com.mrchef.adapter.FoodConsumptionListAdapter;
import com.mrchef.databinding.ActivityFoodConsumptionBinding;
import com.mrchef.databinding.ActivityFoodSelectionBinding;
import com.mrchef.login.LoginActivity;
import com.mrchef.login.User;
import com.mrchef.model.consumption_model.UserFoodItemReport;
import com.mrchef.model.consumption_model.UserMenuReport;
import com.mrchef.network.IMrChefApi;
import com.mrchef.network.NetworkUtils;
import com.mrchef.widget.CustomProgressDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FoodConsumptionDetailActivity extends AppCompatActivity {
  private ActivityFoodConsumptionBinding activityFoodConsumptionBinding;
  private UserMenuReport foodConsumptionDetail;
  private CustomProgressDialog customProgressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityFoodConsumptionBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_food_consumption);
    setSupportActionBar(activityFoodConsumptionBinding.myToolbar.myToolbar);
    activityFoodConsumptionBinding.myToolbar.myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(FoodConsumptionDetailActivity.this, LoginActivity.class));
      }
    });
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    activityFoodConsumptionBinding.inputDoj.setText(dateFormat.format(date));
    fetchDOJDate();
  }
  private void fetchDOJDate() {
    final Calendar myCalendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel(myCalendar);
        callApi();
      }

    };

    activityFoodConsumptionBinding.inputDoj.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        new DatePickerDialog(FoodConsumptionDetailActivity.this, date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    });
  }
  private void callApi() {
//    Retrofit retrofit = new Retrofit.Builder().baseUrl("").build(); // add the base Url
//    IMrChefApi service = retrofit.create(IMrChefApi.class);
    IMrChefApi iMrChefApi = NetworkUtils.getClient().create(IMrChefApi.class);
    final ProgressDialog progressDialog =
            new ProgressDialog(FoodConsumptionDetailActivity.this, R.style.AppTheme_Dark_Dialog);
    progressDialog.show();
    Call<UserMenuReport> booleanCall =
            iMrChefApi.reportsByDate(activityFoodConsumptionBinding.inputDoj.getText().toString());
    booleanCall.enqueue(new Callback<UserMenuReport>() {
      @Override
      public void onResponse(Call<UserMenuReport> call, Response<UserMenuReport> response) {
        progressDialog.dismiss();
        loadDataToRecyclerView(response.body().getUserFoodItemReportList());
      }

      @Override
      public void onFailure(Call<UserMenuReport> call, Throwable t) {
        // no implementation required

      }
    });
  }


  private void loadDataToRecyclerView(List<UserFoodItemReport> menuItemList) {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    activityFoodConsumptionBinding.rvFoodList.setLayoutManager(linearLayoutManager);
    FoodConsumptionListAdapter foodListingAdapter =
        new FoodConsumptionListAdapter(menuItemList, this);
    activityFoodConsumptionBinding.rvFoodList.setAdapter(foodListingAdapter);
  }

  private void updateLabel(Calendar myCalendar) {
    String myFormat = "dd-MM-yyyy"; // In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    activityFoodConsumptionBinding.inputDoj.setText(sdf.format(myCalendar.getTime()));
  }
}
