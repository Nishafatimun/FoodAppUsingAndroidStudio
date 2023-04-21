package com.example.fooddeliverfortrain.customer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fooddeliverfortrain.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CustomerShowFood extends AppCompatActivity {

    ImageView iv_foodImage;
    TextView tv_foodName;
    TextView tv_foodPrice;
    TextView tv_foodDesc;

    private ProgressDialog progressDialog;

    Button btn_addToCart;
    Intent intent;
    String restName = "";
    String foodImage = "";
    String foodPrice = "";
    String foodDesc = "";
    String foodName = "";
    String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_show_food);

        iv_foodImage = findViewById(R.id.iv_foodImage);
        tv_foodName = findViewById(R.id.tv_foodName);
        tv_foodPrice = findViewById(R.id.tv_foodPrice);
        tv_foodDesc = findViewById(R.id.tv_foodDesc);
        btn_addToCart = findViewById(R.id.btn_addToCart);

        intent = getIntent();
        restName = intent.getStringExtra("restName");
        foodImage = intent.getStringExtra("foodImage");
        foodPrice = intent.getStringExtra("foodPrice");
        foodDesc = intent.getStringExtra("foodDesc");
        foodName = intent.getStringExtra("foodName");
        userName = intent.getStringExtra("userName");

        progressDialog = new ProgressDialog(CustomerShowFood.this);

        setUi(foodImage, foodPrice, foodDesc, foodName);

        btn_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Adding Your Food");
                progressDialog.setTitle("Adding...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                addToCartFirebase();
            }
        });

    }

    private void setUi(String foodImage, String foodPrice, String foodDesc, String foodName) {
        tv_foodName.setText(foodName);
        tv_foodPrice.setText(foodPrice);
        tv_foodDesc.setText(foodDesc);
        Glide.with(CustomerShowFood.this).load(foodImage).into(iv_foodImage);
    }

    private void addToCartFirebase() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(CustomerRegister.CART).child(userId);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("foodName", foodName);
        hashMap.put("foodDesc", foodDesc);
        hashMap.put("foodPrice", foodPrice);
        hashMap.put("foodImage", foodImage);
        hashMap.put("restName", restName);
        hashMap.put("userName", userName);
        hashMap.put("id", userId);
        reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(CustomerShowFood.this, "Foood Added To Cart Successfully " + userName, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CustomerShowFood.this, "Food Adding Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}