package com.mrchef.adapter;

import java.util.List;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrchef.R;
import com.mrchef.databinding.ItemFoodListBinding;
import com.mrchef.food_detail.FoodSelectionActivity;
import com.mrchef.food_detail.models.FoodItem;
import com.mrchef.food_detail.models.UserMenuItem;
import com.mrchef.model.food_listing_model.MenuItem;

/**
 * Created by vinod on 07/07/17.
 */

public class FoodListingAdapter
    extends RecyclerView.Adapter<FoodListingAdapter.FoodListingViewHolder> {
  private FoodSelectionActivity foodSelectionActivity;
  private List<FoodItem> foodItemList;

  public FoodListingAdapter(List<FoodItem> foodItemList, FoodSelectionActivity foodSelectionActivity) {
    this.foodItemList = foodItemList;
    this.foodSelectionActivity = foodSelectionActivity;
  }

  @Override
  public FoodListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_list, parent, false);
    return new FoodListingViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final FoodListingViewHolder holder, int position) {
    final FoodItem foodItem = foodItemList.get(position);
    holder.itemFoodListBinding.tvFoodName.setText(foodItem.getName());
    holder.itemFoodListBinding.tvFoodType.setText(foodItem.getItemType());
    Glide.with(foodSelectionActivity).load(foodItem.getImageUrl()).placeholder(R.mipmap.ic_launcher)
            .into(holder.itemFoodListBinding.ivFood);
    holder.itemFoodListBinding.ratingBar.setRating(foodItem.getRating());
    holder.itemFoodListBinding.cbYes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (holder.itemFoodListBinding.cbYes.isChecked()) {
          UserMenuItem userMenuItem = new UserMenuItem();
          userMenuItem.consuming = true;
          userMenuItem.setFoodId(foodItem.getId());
          foodSelectionActivity.userMenuItemList.add(userMenuItem);
        } else {
          UserMenuItem userMenuItem = new UserMenuItem();
          userMenuItem.setFoodId(foodItem.getId());
          if (foodSelectionActivity.userMenuItemList.contains(userMenuItem)) {
            foodSelectionActivity.userMenuItemList.remove(userMenuItem);
          }
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return foodItemList.size();
  }

  class FoodListingViewHolder extends RecyclerView.ViewHolder {
    ItemFoodListBinding itemFoodListBinding;

    FoodListingViewHolder(View itemView) {
      super(itemView);
      itemFoodListBinding = DataBindingUtil.bind(itemView);
    }
  }
}
