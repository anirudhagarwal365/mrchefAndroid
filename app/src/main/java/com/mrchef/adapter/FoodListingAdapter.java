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
import com.mrchef.model.food_listing_model.MenuItem;

/**
 * Created by vinod on 07/07/17.
 */

public class FoodListingAdapter
    extends RecyclerView.Adapter<FoodListingAdapter.FoodListingViewHolder> {
  private final Context context;
  private List<MenuItem> foodMenuList;

  public FoodListingAdapter(List<MenuItem> foodMenuList, Context context) {
    this.foodMenuList = foodMenuList;
    this.context = context;
  }

  @Override
  public FoodListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_list, parent, false);
    return new FoodListingViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final FoodListingViewHolder holder, int position) {
    final MenuItem foodMenu = foodMenuList.get(position);
    holder.itemFoodListBinding.tvFoodName.setText(foodMenu.getName());
    holder.itemFoodListBinding.tvFoodType.setText(foodMenu.getItemType());
    Glide.with(context).load(foodMenu.getImageUrl()).placeholder(R.mipmap.ic_launcher)
        .into(holder.itemFoodListBinding.ivFood);
    holder.itemFoodListBinding.cbYes.setChecked(foodMenu.isSelected());
    holder.itemFoodListBinding.cbYes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (holder.itemFoodListBinding.cbYes.isChecked()) {
          foodMenuList.get(holder.getAdapterPosition()).setSelected(true);
        } else {
          foodMenuList.get(holder.getAdapterPosition()).setSelected(false);

        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return foodMenuList.size();
  }

  class FoodListingViewHolder extends RecyclerView.ViewHolder {
    ItemFoodListBinding itemFoodListBinding;

    FoodListingViewHolder(View itemView) {
      super(itemView);
      itemFoodListBinding = DataBindingUtil.bind(itemView);
    }
  }
}
