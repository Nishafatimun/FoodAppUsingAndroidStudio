package com.example.fooddeliverfortrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliverfortrain.R;
import com.example.fooddeliverfortrain.model.RestaurantFood;

import java.util.ArrayList;
import java.util.List;

public class RestaurantShowFoodAdapter extends RecyclerView.Adapter<RestaurantShowFoodAdapter.viewHolder> {

    private Context mContext;
    private List<RestaurantFood> mRestaurantFoodList = new ArrayList<>();

    public RestaurantShowFoodAdapter(Context mContext, List<RestaurantFood> mRestaurantFoodList) {
        this.mContext = mContext;
        this.mRestaurantFoodList = mRestaurantFoodList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rest_food_layout, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        RestaurantFood food = mRestaurantFoodList.get(position);
        holder.tv_foodDesc.setText(food.getFoodDesc());
        holder.tv_foodName.setText(food.getFoodName());
        holder.tv_foodPrice.setText(food.getFoodPrice());
        Glide.with(mContext).load(food.getFoodImage()).into(holder.iv_foodImage);
    }

    @Override
    public int getItemCount() {
        return mRestaurantFoodList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_foodImage;
        private TextView tv_foodName;
        private TextView tv_foodPrice;
        private TextView tv_foodDesc;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            iv_foodImage = itemView.findViewById(R.id.iv_foodImage);
            tv_foodName = itemView.findViewById(R.id.tv_foodName);
            tv_foodPrice = itemView.findViewById(R.id.tv_foodPrice);
            tv_foodDesc = itemView.findViewById(R.id.tv_foodDesc);
        }
    }
}
