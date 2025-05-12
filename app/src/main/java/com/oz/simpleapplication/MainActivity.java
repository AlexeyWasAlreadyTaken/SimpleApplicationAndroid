package com.oz.simpleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oz.simpleapplication.adapter.CategoryAdapter;
import com.oz.simpleapplication.adapter.ContentAdapter;
import com.oz.simpleapplication.model.Category;
import com.oz.simpleapplication.model.Content;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import com.oz.simpleapplication.DTO.ProductDTO;
import com.oz.simpleapplication.APIInteractions.APIInteractions;

public class MainActivity extends AppCompatActivity {

    RecyclerView categoryRecycler,contentRecycler;
    CategoryAdapter categoryAdapter;
    static ContentAdapter contentAdapter;
    static List<Content> contentList = new ArrayList<>();
    public static List<Content> AllContentList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(0,"All"));
        categoryList.add(new Category(1,"Sales"));
        categoryList.add(new Category(2,"Account"));
        categoryList.add(new Category(3,"Blank"));

        setCategoryRecycler(categoryList);


        // Запрашиваем данные из API
        APIInteractions api = new APIInteractions();
        api.getListOfProducts(new APIInteractions.ProductListCallback() {
            @Override
            public void onSuccess(List<ProductDTO> products) {
                for (ProductDTO p : products) {
                    int typeNumber = 0;
                    switch (p.typeId.toUpperCase()) {
                        case "0951040C-849A-4F0C-B0E5-775675AF1E15":
                            typeNumber = 1;
                            break;
                        case "6DBC1352-415C-411B-8C7C-34582FA8F24F":
                            typeNumber = 2;
                            break;
                        case "2E1AECD8-6DDF-4ABA-AA83-F67D02B7E122":
                            typeNumber = 3;
                            break;
                    }

                    contentList.add(new Content(
                            p.id.trim(),
                            p.name.trim(),
                            p.pictureName.trim(),
                            p.description.trim(),
                            p.typeName.trim(),
                            p.colorCode.trim(),
                            p.fullDescription.trim(),
                            typeNumber));
                }

                AllContentList.clear();
                AllContentList.addAll(contentList);


                // Важно: вызывать здесь, когда данные готовы
                runOnUiThread(() -> setContentRecycler(contentList));
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("API", errorMessage);
            }
        });

//        contentList.add(new Content("1","Sales Service","ani_raccoon","Improve sales","Sales","#154c79","Sales sercice sales improve sales etc...",1));
//        contentList.add(new Content("2","Sales Service","ani_raccoon","Audits sales","Sales","#154c79","Sales service sales Audit sales etc...",1));
//        contentList.add(new Content("3","Account Service","ani_wild","Audits","Account","#76b5c5","Account service account audits etc...",2));
//        contentList.add(new Content("4","BLANK","ani_cow","BLANK","Blank","#873e23","BLANKBLANKBLANKBLANKBLANK...",3));
//        contentList.add(new Content("5","BLANK","ani_cow","BLANK","Blank","#873e23","BLANKBLANKBLANKBLANKBLANK...",3));
//        contentList.add(new Content("6","BLANK","ani_cow","BLANK","Blank","#873e23","BLANKBLANKBLANKBLANKBLANK...",3));
//        AllContentList.addAll(contentList);
//        setContentRecycler(contentList);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this,categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }
    public static void showContentByCategory(int categoryId){

        contentList.clear();
        contentList.addAll(AllContentList);
        List<Content> filterContent = new ArrayList<>();

        if(categoryId == 0){
            filterContent.addAll(AllContentList);
        }else {
            for(Content c : contentList){
                if(c.getCategory() == categoryId){
                    filterContent.add(c);
                }
            }
        }

        contentList.clear();
        contentList.addAll(filterContent);

        contentAdapter.notifyDataSetChanged();
    }

    public void openCart(View view){
        Intent intent = new Intent(this, OrderPage.class);
        startActivity(intent);
    }

    private void setContentRecycler(List<Content> contentList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);

        contentRecycler = findViewById(R.id.contentRecucler);
        contentRecycler.setLayoutManager(layoutManager);

        contentAdapter = new ContentAdapter(this,contentList);
        contentRecycler.setAdapter(contentAdapter);
    }
}