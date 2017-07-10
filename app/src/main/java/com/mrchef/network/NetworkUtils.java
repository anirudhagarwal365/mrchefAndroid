package com.mrchef.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by PrashantKumar on 7/10/17.
 */

public class NetworkUtils {

  public static final String BASE_URL = "http://10.177.1.133:8080/";
  private static Retrofit retrofit = null;


  public static Retrofit getClient() {
    if (retrofit == null) {
      retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create()).build();
    }
    return retrofit;
  }
}
