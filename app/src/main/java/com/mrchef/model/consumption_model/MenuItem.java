
package com.mrchef.model.consumption_model;

import com.google.gson.annotations.SerializedName;

public class MenuItem {

  @SerializedName("count")
  private Long mCount;
  @SerializedName("id")
  private Long mId;
  @SerializedName("imageUrl")
  private String mImageUrl;
  @SerializedName("item_type")
  private String mItemType;
  @SerializedName("name")
  private String mName;

  public Long getCount() {
    return mCount;
  }

  public void setCount(Long count) {
    mCount = count;
  }

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

}
