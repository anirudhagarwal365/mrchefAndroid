package com.mrchef.thankyou;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.mrchef.R;
import com.mrchef.databinding.ActivityThankYouBinding;
import com.mrchef.login.LoginActivity;
import com.mrchef.model.consumption_model.FoodConsumptionDetail;

public class ThankYouActivity extends AppCompatActivity {
  private ActivityThankYouBinding activityThankYouBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityThankYouBinding = DataBindingUtil.setContentView(this, R.layout.activity_thank_you);
    GlideDrawableImageViewTarget imageViewTarget =
        new GlideDrawableImageViewTarget(activityThankYouBinding.ivThankYou);
    Glide.with(this).load(R.drawable.smiley).into(imageViewTarget);
    activityThankYouBinding.btShowReport.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(ThankYouActivity.this, FoodConsumptionDetail.class));
      }
    });
    activityThankYouBinding.toolbar.myToolbar
        .setNavigationOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            onBackPressed();
          }
        });
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(this, LoginActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    super.onBackPressed();
  }
}
