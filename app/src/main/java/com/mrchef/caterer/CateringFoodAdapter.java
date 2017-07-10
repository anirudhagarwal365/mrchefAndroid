package com.mrchef.caterer;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrchef.R;
import com.mrchef.databinding.ItemCateringFoodBinding;

import java.util.List;

/**
 * Created by PrashantKumar on 7/10/17.
 */

public class CateringFoodAdapter
    extends RecyclerView.Adapter<CateringFoodAdapter.FoodItemViewHolder> {
  private List<FoodItem> foodItemList;

  public CateringFoodAdapter(List<FoodItem> foodItemList) {
    this.foodItemList = foodItemList;
  }

  @Override
  public FoodItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catering_food,
        parent, false);
    return new FoodItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final FoodItemViewHolder holder, int position) {
    holder.itemCateringFoodBinding.tvFoodName
        .setText("Food Name : " + foodItemList.get(position).getName());
    holder.itemCateringFoodBinding.tvFoodType
        .setText("Food Type : " + foodItemList.get(position).getItemType());
    holder.itemCateringFoodBinding.ibCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        foodItemList.remove(holder.getAdapterPosition());
        notifyItemRemoved(holder.getAdapterPosition());
      }
    });
  }

  @Override
  public int getItemCount() {
    return foodItemList.size();
  }

  public class FoodItemViewHolder extends RecyclerView.ViewHolder {
    ItemCateringFoodBinding itemCateringFoodBinding;

    public FoodItemViewHolder(View itemView) {
      super(itemView);
      itemCateringFoodBinding = DataBindingUtil.bind(itemView);
    }
  }
}
