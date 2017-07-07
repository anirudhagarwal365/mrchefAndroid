
package com.mrchef.model.food_listing_model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FoodMenu {

  @SerializedName("date")
  private String mDate;
  @SerializedName("menu_items")
  private List<MenuItem> mMenuItems;
  @SerializedName("vendor")
  private String mVendor;

  public String getDate() {
    return mDate;
  }

  public void setDate(String date) {
    mDate = date;
  }

  public List<MenuItem> getMenuItems() {
    return mMenuItems;
  }

  public void setMenuItems(List<MenuItem> menuItems) {
    mMenuItems = menuItems;
  }

  public String getVendor() {
    return mVendor;
  }

  public void setVendor(String vendor) {
    mVendor = vendor;
  }

}
