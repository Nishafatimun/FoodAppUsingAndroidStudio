package com.example.fooddeliverfortrain.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fooddeliverfortrain.R;
import com.example.fooddeliverfortrain.customer.CustomerRegister;
import com.example.fooddeliverfortrain.model.CustomerLocationModel;
import com.example.fooddeliverfortrain.model.RiderFood;
import com.example.fooddeliverfortrain.restaurant.RestaurantRegister;
import com.example.fooddeliverfortrain.rider.RiderUserLocation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RiderShowFoodAdapter extends RecyclerView.Adapter<RiderShowFoodAdapter.viewHolder> {
    private Context mContext;
    private List<RiderFood> mRestaurantFoodList = new ArrayList<>();

    public RiderShowFoodAdapter(Context mContext, List<RiderFood> mRestaurantFoodList) {
        this.mContext = mContext;
        this.mRestaurantFoodList = mRestaurantFoodList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rider_layout_show_order, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        RiderFood food = mRestaurantFoodList.get(position);
        holder.tv_restName.setText(food.getRestName());
        holder.tv_userName.setText(food.getUserName());
        holder.tv_foodName.setText(food.getFoodName());
        Glide.with(mContext).load(food.getFoodImage()).into(holder.cv_foodImage);

        holder.btn_userLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser.getUid() != null) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(CustomerRegister.LOCATION_CUSTOMERS).child(food.getUserName());
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            CustomerLocationModel model = snapshot.getValue(CustomerLocationModel.class);
                            Double latitude = Double.parseDouble(model.getLatitude());
                            Double longitude = Double.parseDouble(model.getLongitude());
                            Intent intent = new Intent(mContext, RiderUserLocation.class);
                            intent.putExtra("latitude", latitude);
                            intent.putExtra("longitude", longitude);
                            mContext.startActivity(intent);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        holder.btn_restLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser.getUid() != null) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(RestaurantRegister.LOCATION_RESTAURANTS).child(food.getRestName());
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            CustomerLocationModel model = snapshot.getValue(CustomerLocationModel.class);
                            Double latitude = Double.parseDouble(model.getLatitude());
                            Double longitude = Double.parseDouble(model.getLongitude());
                            Intent intent = new Intent(mContext, RiderUserLocation.class);
                            intent.putExtra("latitude", latitude);
                            intent.putExtra("longitude", longitude);
                            mContext.startActivity(intent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        holder.btn_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(mContext);
                progressDialog.setTitle("Complete...");
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser.getUid() != null) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(CustomerRegister.ORDER).child(food.getRestName()).child(food.getId()).child(food.getFoodId());
                    reference.removeValue();
                    progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRestaurantFoodList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {

        private ImageView cv_foodImage;
        private TextView tv_userName;
        private TextView tv_foodName;
        private TextView tv_restName;
        private Button btn_userLocation;
        private Button btn_restLocation;
        private Button btn_completed;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cv_foodImage = itemView.findViewById(R.id.cv_foodImage);
            tv_userName = itemView.findViewById(R.id.tv_userName);
            tv_foodName = itemView.findViewById(R.id.tv_foodName);
            tv_restName = itemView.findViewById(R.id.tv_restName);
            btn_userLocation = itemView.findViewById(R.id.btn_userLocation);
            btn_restLocation = itemView.findViewById(R.id.btn_restLocation);
            btn_completed = itemView.findViewById(R.id.btn_completed);
        }
    }
}
