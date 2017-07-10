package com.mrchef.food_detail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.mrchef.R;
import com.mrchef.adapter.FoodListingAdapter;
import com.mrchef.databinding.ActivityFoodSelectionBinding;
import com.mrchef.food_detail.models.CatererDetailsAndMenu;
import com.mrchef.food_detail.models.FoodItem;
import com.mrchef.food_detail.models.SubmitMenuDetail;
import com.mrchef.food_detail.models.UserMenuItem;
import com.mrchef.login.LoginActivity;
import com.mrchef.login.User;
import com.mrchef.model.food_listing_model.FoodMenu;
import com.mrchef.model.food_listing_model.MenuItem;
import com.mrchef.network.IMrChefApi;
import com.mrchef.network.NetworkUtils;
import com.mrchef.thankyou.ThankYouActivity;
import com.mrchef.widget.CustomProgressDialog;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FoodSelectionActivity extends AppCompatActivity {
  private ActivityFoodSelectionBinding activityFoodSelectionBinding;
  private FoodMenu foodMenu;
  private CustomProgressDialog customProgressDialog;

  private String dateToSend;
  private boolean takingFood;
  public List<UserMenuItem> userMenuItemList = new ArrayList<>();

  private String name;
  private String empId;
  private String doj;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityFoodSelectionBinding =
        DataBindingUtil.setContentView(this, R.layout.activity_food_selection);
    setSupportActionBar(activityFoodSelectionBinding.myToolbar.myToolbar);
    name = getIntent().getStringExtra("name");
    empId = getIntent().getStringExtra("empId");
    doj = getIntent().getStringExtra("doj");
    initializeDate();
    customProgressDialog = new CustomProgressDialog(this, 0, true);
    customProgressDialog.show();
    activityFoodSelectionBinding.myToolbar.myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });
    activityFoodSelectionBinding.cbTakingFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        takingFood = isChecked;
      }
    });
    callApi();
  }
  private void updateLabel(Calendar myCalendar) {
    String myFormat = "dd-MM-yyyy"; // In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    dateToSend = sdf.format(myCalendar.getTime());
    activityFoodSelectionBinding.datePicker.setText(sdf.format(myCalendar.getTime()));
    callApi();
  }


  private void initializeDate(){
    final Calendar myCalendar = Calendar.getInstance();

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date currentDate = new Date();
    dateToSend = dateFormat.format(currentDate);
    activityFoodSelectionBinding.datePicker.setText(dateFormat.format(currentDate));


    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel(myCalendar);
      }
    };

    activityFoodSelectionBinding.datePicker.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new DatePickerDialog(FoodSelectionActivity.this, date, myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    });
  }

  private void callApi() {

    final ProgressDialog progressDialog =
        new ProgressDialog(FoodSelectionActivity.this, R.style.AppTheme_Dark_Dialog);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Chefs Brainstorming!! Deciding the menu!! Please wait...");
    progressDialog.show();

    IMrChefApi iMrChefApi = NetworkUtils.getClient().create(IMrChefApi.class);
    Call<CatererDetailsAndMenu> catererDetailsAndMenuCall = iMrChefApi.menuRequest(dateToSend);
    catererDetailsAndMenuCall.enqueue(new Callback<CatererDetailsAndMenu>() {

      @Override
      public void onResponse(Call<CatererDetailsAndMenu> call,
          Response<CatererDetailsAndMenu> response) {
        progressDialog.dismiss();
        customProgressDialog.dismiss();
        CatererDetailsAndMenu catererDetailsAndMenu = response.body();
        if (catererDetailsAndMenu != null) {
          loadDataToRecyclerView(catererDetailsAndMenu.getFoodItems());
        }
      }
      @Override
      public void onFailure(Call<CatererDetailsAndMenu> call, Throwable t) {

      }
    });
  }

  private void callApiForSelecting(){

    IMrChefApi iMrChefApi = NetworkUtils.getClient().create(IMrChefApi.class);

    SubmitMenuDetail submitMenuDetail = new SubmitMenuDetail();
    submitMenuDetail.setUser(new User(empId, name, doj));
    submitMenuDetail.setDate(dateToSend);
    submitMenuDetail.setTakingFood(takingFood);
    if (takingFood) {
      submitMenuDetail.setUserMenuItems(userMenuItemList);
    }

    Call<Boolean> booleanCall = iMrChefApi.submitMenu(submitMenuDetail);
    booleanCall.enqueue(new Callback<Boolean>() {
      @Override
      public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        customProgressDialog.dismiss();
        Toast.makeText(FoodSelectionActivity.this, "done", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(FoodSelectionActivity.this, ThankYouActivity.class));
      }

      @Override
      public void onFailure(Call<Boolean> call, Throwable t) {

      }
    });
  }

  private void loadDataToRecyclerView(List<FoodItem> foodItemList) {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    activityFoodSelectionBinding.rvFoodList.setLayoutManager(linearLayoutManager);
    FoodListingAdapter foodListingAdapter = new FoodListingAdapter(foodItemList, this);
    activityFoodSelectionBinding.rvFoodList.setAdapter(foodListingAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(android.view.MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    // noinspection SimplifiableIfStatement
    if (id == R.id.action_submit) {
      customProgressDialog.show();
      callApiForSelecting();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
