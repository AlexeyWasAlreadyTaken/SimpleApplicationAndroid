package com.oz.simpleapplication.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.oz.simpleapplication.ContentPage;
import com.oz.simpleapplication.R;
import com.oz.simpleapplication.model.Content;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    public ContentAdapter(Context context, List<Content> contents) {
        this.context = context;
        this.contents = contents;
    }

    Context context;
    List<Content> contents;

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentItems = LayoutInflater.from(context).inflate(R.layout.content_item,parent,false);
        return new ContentAdapter.ContentViewHolder(contentItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        holder.contentBg.setCardBackgroundColor(Color.parseColor(contents.get(position).getColor()));

        int imageId;
        imageId = context.getResources().getIdentifier (contents.get(position).getImg(),"drawable",context.getPackageName());
        holder.contentImage.setImageResource(imageId);

        String title = contents.get(position).getTitle();
        holder.contentTitle.setText(title);

        String description = contents.get(position).getDescription();
        holder.contentDescription.setText(description);

        String property = contents.get(position).getProperty();
        holder.contentProperty.setText(property);

        String contentFullText = contents.get(position).getText();

        int contentId = contents.get(position).getId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(context, ContentPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        new Pair<View,String>(holder.contentImage,"contentImage")
                );

                intent.putExtra("main",Color.parseColor(contents.get(position).getColor()));
                intent.putExtra("contentPageImage",imageId);
                intent.putExtra("contantPageTitle",title);
                intent.putExtra("content_page_desc_title_value",description);
                intent.putExtra("content_page_prop_title_value",property);
                intent.putExtra("contentpage_text",contentFullText);
                intent.putExtra("content_id",contentId);


                context.startActivity(intent,options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public static final class ContentViewHolder extends RecyclerView.ViewHolder {

        CardView contentBg;
        ImageView contentImage;
        TextView contentTitle;
        TextView contentDescription;
        TextView contentProperty;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            contentBg = itemView.findViewById(R.id.contentbg);
            contentImage = itemView.findViewById(R.id.content_image);
            contentTitle = itemView.findViewById(R.id.content_title);
            contentDescription = itemView.findViewById(R.id.content_desc_title_value);
            contentProperty = itemView.findViewById(R.id.content_prop_title_value);
        }
    }
}
