
package com.mrchef.model.consumption_model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FoodConsumptionDetail {

  @SerializedName("date")
  private String mDate;
  @SerializedName("menu_items")
  private List<MenuItem> mMenuItems;
  @SerializedName("no_of_member_taking_food")
  private Long mNoOfMemberTakingFood;
  @SerializedName("total_member")
  private String mTotalMember;
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

  public Long getNoOfMemberTakingFood() {
    return mNoOfMemberTakingFood;
  }

  public void setNoOfMemberTakingFood(Long noOfMemberTakingFood) {
    mNoOfMemberTakingFood = noOfMemberTakingFood;
  }

  public String getTotalMember() {
    return mTotalMember;
  }

  public void setTotalMember(String totalMember) {
    mTotalMember = totalMember;
  }

  public String getVendor() {
    return mVendor;
  }

  public void setVendor(String vendor) {
    mVendor = vendor;
  }

}
