package com.mrchef.food_detail.models;

/**
 * Created by anirudh on 10/07/17.
 */

public class UserMenuItem {
  public String foodId;
  public boolean consuming;

  public String getFoodId() {
    return foodId;
  }

  public void setFoodId(String foodId) {
    this.foodId = foodId;
  }

  public boolean isConsuming() {
    return consuming;
  }

  public void setConsuming(boolean consuming) {
    this.consuming = consuming;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserMenuItem that = (UserMenuItem) o;

    return foodId != null ? foodId.equals(that.foodId) : that.foodId == null;

  }

  @Override
  public int hashCode() {
    return foodId != null ? foodId.hashCode() : 0;
  }
}
