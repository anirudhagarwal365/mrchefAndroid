package com.mrchef.adapter;

import java.util.List;

import com.bumptech.glide.Glide;
import com.mrchef.R;
import com.mrchef.databinding.ItemConsumptionDetailBinding;
import com.mrchef.model.consumption_model.UserFoodItemReport;
import com.mrchef.model.consumption_model.UserFoodItemReport;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by vinod on 07/07/17.
 */

public class FoodConsumptionListAdapter
    extends RecyclerView.Adapter<FoodConsumptionListAdapter.FoodConsumptionViewHolder> {
  private final Context context;
  private List<UserFoodItemReport> menuItemList;

  public FoodConsumptionListAdapter(List<UserFoodItemReport> menuItemList, Context context) {
    this.menuItemList = menuItemList;
    this.context = context;
  }

  @Override
  public FoodConsumptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consumption_detail,
        parent, false);
    return new FoodConsumptionViewHolder(view);
  }

  @Override
  public void onBindViewHolder(FoodConsumptionViewHolder holder, int position) {
    final UserFoodItemReport menuItem = menuItemList.get(position);
    holder.itemConsumptionDetailBinding.tvFoodName.setText(menuItem.getName());
    holder.itemConsumptionDetailBinding.tvFoodType.setText(menuItem.getItemType());
    holder.itemConsumptionDetailBinding.tvNumberOfPeople
        .setText(String.valueOf(menuItem.getCount()));
    Glide.with(context).load(menuItem.getImageUrl())
        .into(holder.itemConsumptionDetailBinding.ivFood);
    holder.itemConsumptionDetailBinding.rbStar.setRating(menuItem.getRating());
  }

  @Override
  public int getItemCount() {
    return menuItemList.size();
  }

  class FoodConsumptionViewHolder extends RecyclerView.ViewHolder {
    ItemConsumptionDetailBinding itemConsumptionDetailBinding;

    public FoodConsumptionViewHolder(View itemView) {
      super(itemView);
      itemConsumptionDetailBinding = DataBindingUtil.bind(itemView);
    }
  }


}
