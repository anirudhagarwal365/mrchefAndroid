package com.mrchef.caterer;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.mrchef.R;
import com.mrchef.databinding.ActivityCaterorBinding;

public class CatererActivity extends AppCompatActivity {
  private ActivityCaterorBinding activityCaterorBinding;
  private String[] foodTypes = {"Main Course", "Desert", "Snacks"};
  private String foodType;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityCaterorBinding = DataBindingUtil.setContentView(this, R.layout.activity_cateror);
    setSupportActionBar(activityCaterorBinding.myToolbar.myToolbar);
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
      Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
