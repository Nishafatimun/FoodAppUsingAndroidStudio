package com.example.fooddeliverfortrain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliverfortrain.R;
import com.example.fooddeliverfortrain.model.CustomerCartModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomerCartAdapter extends RecyclerView.Adapter<CustomerCartAdapter.viewHolder> {

    private Context mContext;
    private List<CustomerCartModel> mRestaurantFoodList = new ArrayList<>();

    public CustomerCartAdapter(Context mContext, List<CustomerCartModel> mRestaurantFoodList) {
        this.mContext = mContext;
        this.mRestaurantFoodList = mRestaurantFoodList;
    }

    @NonNull
    @Override
    public CustomerCartAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent, false);
        return new CustomerCartAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerCartAdapter.viewHolder holder, int position) {
        CustomerCartModel food = mRestaurantFoodList.get(position);
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

        private CircleImageView iv_foodImage;
        private TextView tv_foodName;
        private TextView tv_foodPrice;
        private TextView tv_foodDesc;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            iv_foodImage = itemView.findViewById(R.id.cv_foodImage);
            tv_foodName = itemView.findViewById(R.id.tv_foodName);
            tv_foodPrice = itemView.findViewById(R.id.tv_foodPrice);
            tv_foodDesc = itemView.findViewById(R.id.tv_foodDesc);


        }
    }

}
