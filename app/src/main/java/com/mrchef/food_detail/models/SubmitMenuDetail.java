package com.mrchef.food_detail.models;

import com.mrchef.login.User;

import java.util.List;

/**
 * Created by anirudh on 10/07/17.
 */

public class SubmitMenuDetail {

  public User user;
  public String date;
  public boolean takingFood;
  public List<UserMenuItem> userMenuItems;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public boolean isTakingFood() {
    return takingFood;
  }

  public void setTakingFood(boolean takingFood) {
    this.takingFood = takingFood;
  }

  public List<UserMenuItem> getUserMenuItems() {
    return userMenuItems;
  }

  public void setUserMenuItems(List<UserMenuItem> userMenuItems) {
    this.userMenuItems = userMenuItems;
  }
}
