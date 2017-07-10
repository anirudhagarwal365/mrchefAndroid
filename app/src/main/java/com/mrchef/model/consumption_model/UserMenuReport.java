
package com.mrchef.model.consumption_model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserMenuReport {
  private int totalMembers;
  private int noOfMembersTakingFood;
  private String date;
  private String vendorName;
  private List<UserFoodItemReport> userFoodItemReportList;

  public int getTotalMembers() {
    return totalMembers;
  }

  public void setTotalMembers(int totalMembers) {
    this.totalMembers = totalMembers;
  }

  public int getNoOfMembersTakingFood() {
    return noOfMembersTakingFood;
  }

  public void setNoOfMembersTakingFood(int noOfMembersTakingFood) {
    this.noOfMembersTakingFood = noOfMembersTakingFood;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getVendorName() {
    return vendorName;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName;
  }

  public List<UserFoodItemReport> getUserFoodItemReportList() {
    return userFoodItemReportList;
  }

  public void setUserFoodItemReportList(List<UserFoodItemReport> userFoodItemReportList) {
    this.userFoodItemReportList = userFoodItemReportList;
  }
}