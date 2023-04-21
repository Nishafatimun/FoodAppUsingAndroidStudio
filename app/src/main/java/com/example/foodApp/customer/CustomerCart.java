package com.example.fooddeliverfortrain.customer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliverfortrain.R;
import com.example.fooddeliverfortrain.adapter.CustomerCartAdapter;
import com.example.fooddeliverfortrain.model.CustomerCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerCart extends AppCompatActivity {

    RecyclerView rv_showCartFood;
    List<CustomerCartModel> mList = new ArrayList<>();

    CustomerCartAdapter mAdapter;

    Button btn_orderFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cart);

        rv_showCartFood = findViewById(R.id.rv_showCartFood);
        rv_showCartFood.setHasFixedSize(true);
        rv_showCartFood.setLayoutManager(new LinearLayoutManager(CustomerCart.this));

        btn_orderFood = findViewById(R.id.btn_orderFood);

        btn_orderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderFood();
            }
        });

        getCartFood();

    }

    private void orderFood() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();
        for (CustomerCartModel food : mList) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(CustomerRegister.ORDER).child(food.getRestName()).child(firebaseUser.getUid());
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("foodName", food.getFoodName());
            hashMap.put("foodDesc", food.getFoodDesc());
            hashMap.put("foodPrice", food.getFoodPrice());
            hashMap.put("foodImage", food.getFoodImage());
            hashMap.put("restName", food.getRestName());
            hashMap.put("userName", food.getUserName());
            hashMap.put("id", userId);
            reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(CustomerCart.this, "Foood Ordered Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CustomerCart.this, "Food Ordered Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void getCartFood() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();
        if (firebaseUser.getUid() != null) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(CustomerRegister.CART).child(userId);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        CustomerCartModel restaurantFood = dataSnapshot.getValue(CustomerCartModel.class);
                        mList.add(restaurantFood);
                    }
                    mAdapter = new CustomerCartAdapter(CustomerCart.this, mList);
                    rv_showCartFood.setAdapter(mAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}