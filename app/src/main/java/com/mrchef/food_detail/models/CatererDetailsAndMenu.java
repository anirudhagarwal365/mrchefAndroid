package com.mrchef.food_detail.models;

import java.util.List;

/**
 * Created by anirudh on 10/07/17.
 */

public class CatererDetailsAndMenu {
  private String foodId;
  private String vendorName;
  private String date; //dd-mm-yyyy
  private List<FoodItem> foodItems;

  public String getFoodId() {
    return foodId;
  }

  public void setFoodId(String foodId) {
    this.foodId = foodId;
  }

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<FoodItem> getFoodItems() {
    return foodItems;
  }

  public void setFoodItems(List<FoodItem> foodItems) {
    this.foodItems = foodItems;
  }
}
