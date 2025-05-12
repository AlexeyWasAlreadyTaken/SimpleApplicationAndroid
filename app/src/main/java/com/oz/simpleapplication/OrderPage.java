package com.oz.simpleapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.oz.simpleapplication.APIInteractions.APIInteractions;
import com.oz.simpleapplication.DTO.OrderCreateDTO;
import com.oz.simpleapplication.DTO.OrderDTO;
import com.oz.simpleapplication.model.Content;
import com.oz.simpleapplication.model.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OrderPage extends AppCompatActivity {

    private static  List<String> orderContentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView orders_list = findViewById(R.id.orderlist);

        List<String> contentTitle = new ArrayList<>();
        for(Content c : MainActivity.AllContentList){
            if(Order.items_guid.contains(c.getId())){
                contentTitle.add(c.getTitle()+". "+c.getDescription());
            }
        }

        orderContentList = contentTitle;
        orders_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contentTitle));
    }

    public void cartSubmit(View view){
        EditText nameInput = findViewById(R.id.NameLabelValue);
        EditText phoneInput = findViewById(R.id.PhoneLabelValue);

        String customerName = nameInput.getText().toString().trim();
        String phoneNumber = phoneInput.getText().toString().trim();

        if (customerName.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Enter name and phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        // Используем реальные ID из корзины
        //TODO GUID for API
        List<String> selectedProductIds = new ArrayList<>(Order.items_guid);
        for (String id : selectedProductIds) {
            Log.d("ProductID", id);
        }

        // Проверка на пустую корзину
        if (selectedProductIds.isEmpty()) {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        OrderCreateDTO newOrder = new OrderCreateDTO(customerName, phoneNumber, selectedProductIds);
        Log.d("API", new Gson().toJson(newOrder));

        APIInteractions api = new APIInteractions();
        api.createOrder(newOrder, new APIInteractions.OrderCreateCallback() {
            @Override
            public void onSuccess(OrderDTO createdOrder) {
                int orderNumber = createdOrder.number;

                runOnUiThread(() -> {
                    new AlertDialog.Builder(OrderPage.this)
                            .setTitle("Order Created")
                            .setMessage("Your order #" + orderNumber + " was submitted successfully.")
                            .setPositiveButton("OK", (dialog, which) -> {
                                Order.items_guid.clear();
                                finish();
                            })
                            .show();
                });
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("API", "Failed to create order: " + errorMessage);
                runOnUiThread(() ->
                        Toast.makeText(OrderPage.this, "Failed to submit order", Toast.LENGTH_SHORT).show()
                );
            }
        });
    }
}