package com.oz.simpleapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.oz.simpleapplication.model.Order;

public class ContentPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_content_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ConstraintLayout constraintLayout = findViewById(R.id.main);
        ImageView contentImage = findViewById(R.id.contentPageImage);
        TextView contentTitle = findViewById(R.id.contantPageTitle);
        TextView contentDesc = findViewById(R.id.content_page_desc_title_value);
        TextView contentProp = findViewById(R.id.content_page_prop_title_value);
        TextView contentText = findViewById(R.id.contentpage_text);

        constraintLayout.setBackgroundColor(getIntent().getIntExtra("main",0));
        contentImage.setImageResource(getIntent().getIntExtra("contentPageImage",0));
        contentTitle.setText(getIntent().getStringExtra("contantPageTitle"));
        contentDesc.setText(getIntent().getStringExtra("content_page_desc_title_value"));
        contentProp.setText(getIntent().getStringExtra("content_page_prop_title_value"));
        contentText.setText(getIntent().getStringExtra("contentpage_text"));

    }

    public void addToCart(View view){
        int item_id = getIntent().getIntExtra("content_id",0);
        Order.items_id.add(item_id);
        Toast.makeText(this,"Added",Toast.LENGTH_LONG).show();
    }
}