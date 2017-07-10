package com.mrchef.caterer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.mrchef.R;
import com.mrchef.databinding.ActivityCaterorBinding;
import com.mrchef.login.LoginActivity;
import com.mrchef.login.User;
import com.mrchef.network.IMrChefApi;
import com.mrchef.network.NetworkUtils;
import com.mrchef.thankyou.ThankYouActivity;
import com.mrchef.widget.CustomProgressDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatererActivity extends AppCompatActivity {
  private ActivityCaterorBinding activityCaterorBinding;
  private String[] foodTypes = {"Select Food Type", "Main Course", "Desert", "Snacks"};
  private String foodType;
  private String foodName;
  private String foodImageUrl;
  private List<FoodItem> foodItemList = new ArrayList<>();
  private CateringFoodAdapter cateringFoodAdapter;
  private CustomProgressDialog customProgressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityCaterorBinding = DataBindingUtil.setContentView(this, R.layout.activity_cateror);
    setSupportActionBar(activityCaterorBinding.myToolbar.myToolbar);
    customProgressDialog = new CustomProgressDialog(this, 0, true);
    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foodTypes);

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    activityCaterorBinding.spinner.setAdapter(dataAdapter);

    activityCaterorBinding.spinner
        .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position != 0) {
              foodType = parent.getItemAtPosition(position).toString();
            }
          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {
            // No implementation required
          }
        });

    activityCaterorBinding.myToolbar.myToolbar
        .setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            onBackPressed();
          }
        });

    addItemToList();
    fetchDateOfFood();
  }

  private void addItemToList() {
    activityCaterorBinding.btAdd.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        foodName = activityCaterorBinding.inputFoodName.getText().toString();
        foodImageUrl = activityCaterorBinding.inputFoodImage.getText().toString();
        foodType = foodTypes[ activityCaterorBinding.spinner.getSelectedItemPosition()];
        foodItemList.add(0, new FoodItem(foodName, foodImageUrl, foodType, 5));
        activityCaterorBinding.inputFoodName.setText("");
        activityCaterorBinding.inputFoodImage.setText("");
        activityCaterorBinding.spinner.setSelection(0);
        showListItem();
      }
    });
  }

  private void showListItem() {
    if (cateringFoodAdapter == null) {
      activityCaterorBinding.rvFoodList.setLayoutManager(new LinearLayoutManager(this));
      cateringFoodAdapter = new CateringFoodAdapter(foodItemList);
      activityCaterorBinding.rvFoodList.setAdapter(cateringFoodAdapter);
    } else {
      cateringFoodAdapter.notifyDataSetChanged();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    // noinspection SimplifiableIfStatement
    if (id == R.id.action_submit) {
      customProgressDialog.show();
      submitAddedFoodItems();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void submitAddedFoodItems() {
    IMrChefApi iMrChefApi = NetworkUtils.getClient().create(IMrChefApi.class);
    Call<Boolean> booleanCall = iMrChefApi.addFoodItems(
        new CatererDetailsAndMenu(activityCaterorBinding.inputVendorName.getText().toString(),
            activityCaterorBinding.inputDate.getText().toString(), foodItemList));
    booleanCall.enqueue(new Callback<Boolean>() {
      @Override
      public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        customProgressDialog.dismiss();
        startActivity(new Intent(CatererActivity.this, ThankYouActivity.class));
        finish();
      }

      @Override
      public void onFailure(Call<Boolean> call, Throwable t) {
        // No implementation required
      }
    });
  }

  private void fetchDateOfFood() {
    final Calendar myCalendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel(myCalendar);
      }

    };

    activityCaterorBinding.inputDate.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        new DatePickerDialog(CatererActivity.this, date, myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    });
  }

  private void updateLabel(Calendar myCalendar) {
    String myFormat = "dd-MM-yyyy"; // In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    activityCaterorBinding.inputDate.setText(sdf.format(myCalendar.getTime()));
  }
}
