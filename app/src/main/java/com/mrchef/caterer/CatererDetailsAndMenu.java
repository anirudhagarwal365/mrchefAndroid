package com.mrchef.caterer;

import java.util.List;

/**
 * Created by PrashantKumar on 7/10/17.
 */

public class CatererDetailsAndMenu {

  private String vendorName;
  private String date;
  private List<FoodItem> foodItems;

  public CatererDetailsAndMenu(String vendorName, String date, List<FoodItem> foodItems) {
    this.vendorName = vendorName;
    this.date = date;
    this.foodItems = foodItems;
  }

}
