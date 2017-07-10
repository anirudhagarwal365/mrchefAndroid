package com.mrchef.caterer;

/**
 * Created by PrashantKumar on 7/10/17.
 */

public class FoodItem {
  private String name;
  private String imageUrl;
  private String itemType;
  private int rating;

  public FoodItem(String name, String imageUrl, String itemType, int rating) {
    this.name = name;
    this.imageUrl = imageUrl;
    this.itemType = itemType;
    this.rating = rating;
  }

  public String getName() {
    return name;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public String getItemType() {
    return itemType;
  }

  public int getRating() {
    return rating;
  }
}
