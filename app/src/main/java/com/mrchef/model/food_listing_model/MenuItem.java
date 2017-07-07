
package com.mrchef.model.food_listing_model;

import com.google.gson.annotations.SerializedName;


public class MenuItem {

  @SerializedName("id")
  private Long mId;
  @SerializedName("imageUrl")
  private String mImageUrl;
  @SerializedName("item_type")
  private String mItemType;
  @SerializedName("name")
  private String mName;
  @SerializedName("isSelected")
  private boolean isSelected;

  public Long getId() {
    return mId;
  }

  public void setId(Long id) {
    mId = id;
  }

  public String getImageUrl() {
    return mImageUrl;
  }

  public void setImageUrl(String imageUrl) {
    mImageUrl = imageUrl;
  }

  public String getItemType() {
    return mItemType;
  }

  public void setItemType(String itemType) {
    mItemType = itemType;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public void setSelected(boolean selected) {
    isSelected = selected;
  }
}
