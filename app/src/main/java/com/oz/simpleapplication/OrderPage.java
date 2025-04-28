package com.oz.simpleapplication;

import android.content.DialogInterface;
import android.os.Bundle;
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

import com.oz.simpleapplication.DTO.OrderDTO;
import com.oz.simpleapplication.model.Content;
import com.oz.simpleapplication.model.Order;

import java.util.ArrayList;
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
        for(Content c : MainActivity.AllСontentList){
            if(Order.items_id.contains(c.getId())){
                contentTitle.add(c.getTitle()+". "+c.getDescription());
            }
        }

        orderContentList = contentTitle;
        orders_list.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contentTitle));
    }

    public void cartSubmit(View view){
        EditText name,phone;
        name = findViewById(R.id.NameLabelValue);
        phone = findViewById(R.id.PhoneLabelValue);

        Random ran = new Random();
        int ordernumber =  ran.nextInt(1000000);

        OrderDTO orderDTO = new OrderDTO(ordernumber,orderContentList,name.getText(),phone.getText());

        String message = "Order number: " + orderDTO.getId() + "\n Name: " + orderDTO.getName() + "\nPhone: " +orderDTO.getPhone() +"\n"+orderDTO.getContents();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle("Order created");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
        });
        builder.setNegativeButton("Close", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}