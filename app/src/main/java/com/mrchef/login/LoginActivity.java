package com.mrchef.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mrchef.R;
import com.mrchef.food_detail.FoodSelectionActivity;
import com.mrchef.network.IMrChefApi;
import com.mrchef.network.NetworkUtils;

import butterknife.ButterKnife;
import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
  private static final String TAG = "LoginActivity";
  private static final int REQUEST_SIGNUP = 0;

  @Bind(R.id.input_email)
  EditText _emailText;
  @Bind(R.id.input_password)
  EditText _passwordText;
  @Bind(R.id.btn_login)
  Button _loginButton;
  @Bind(R.id.link_signup)
  TextView _signupLink;
  @Bind(R.id.input_doj)
  EditText editTextDoj;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);

    _loginButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        login();
      }
    });

    _signupLink.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // Start the Signup activity
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
      }
    });
  }

  public void login() {
    Log.d(TAG, "Login");

//    if (!validate()) {
//      onLoginFailed();
//      return;
//    }

    _loginButton.setEnabled(false);

    final ProgressDialog progressDialog =
        new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
    progressDialog.setIndeterminate(true);
    progressDialog.setMessage("Authenticating...");
    progressDialog.show();

    String employeeId = _emailText.getText().toString();
    String name = _passwordText.getText().toString();
    String doj = editTextDoj.getText().toString();

    IMrChefApi iMrChefApi = NetworkUtils.getClient().create(IMrChefApi.class);
    Call<Boolean> booleanCall =
        iMrChefApi.loginRequest(new User("Ganna420", "Ganna", "20-01-2010"));
    booleanCall.enqueue(new Callback<Boolean>() {
      @Override
      public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        progressDialog.dismiss();
        startActivity(new Intent(LoginActivity.this, FoodSelectionActivity.class));
      }

      @Override
      public void onFailure(Call<Boolean> call, Throwable t) {
        // no
      }
    });
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_SIGNUP) {
      if (resultCode == RESULT_OK) {

        // TODO: Implement successful signup logic here
        // By default we just finish the Activity and log them in automatically
        this.finish();
      }
    }
  }

  @Override
  public void onBackPressed() {
    // Disable going back to the SplashActivity
    moveTaskToBack(true);
  }

  public void onLoginSuccess() {
    _loginButton.setEnabled(true);
    finish();
  }

  public void onLoginFailed() {
    Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

    _loginButton.setEnabled(true);
  }

  public boolean validate() {
    boolean valid = true;

    String email = _emailText.getText().toString();
    String password = _passwordText.getText().toString();

    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      _emailText.setError("enter a valid email address");
      valid = false;
    } else {
      _emailText.setError(null);
    }

    if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
      _passwordText.setError("between 4 and 10 alphanumeric characters");
      valid = false;
    } else {
      _passwordText.setError(null);
    }

    return valid;
  }
}
