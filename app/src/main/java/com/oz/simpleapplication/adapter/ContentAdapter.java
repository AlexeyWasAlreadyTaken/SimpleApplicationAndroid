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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.oz.simpleapplication.ContentPage;
import com.oz.simpleapplication.R;
import com.oz.simpleapplication.model.Content;

import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private final Context context;
    private final List<Content> contents;

    public ContentAdapter(Context context, List<Content> contents) {
        this.context = context;
        this.contents = contents;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentItems = LayoutInflater.from(context).inflate(R.layout.content_item, parent, false);
        return new ContentViewHolder(contentItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        Content currentContent = contents.get(position);

        int imageId = context.getResources().getIdentifier(currentContent.getImg(), "drawable", context.getPackageName());

        holder.contentBg.setCardBackgroundColor(Color.parseColor(currentContent.getColor()));
        holder.contentImage.setImageResource(imageId);
        holder.contentTitle.setText(currentContent.getTitle());
        holder.contentDescription.setText(currentContent.getDescription());
        holder.contentProperty.setText(currentContent.getProperty());

        holder.itemView.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition == RecyclerView.NO_POSITION) return;

            Intent intent = new Intent(context, ContentPage.class);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                    (Activity) context,
                    new Pair<>(holder.contentImage, "contentImage")
            );

            intent.putExtra("main", Color.parseColor(currentContent.getColor()));
            intent.putExtra("contentPageImage", imageId);
            intent.putExtra("contantPageTitle", currentContent.getTitle());
            intent.putExtra("content_page_desc_title_value", currentContent.getDescription());
            intent.putExtra("content_page_prop_title_value", currentContent.getProperty());
            intent.putExtra("contentpage_text", currentContent.getText());
            intent.putExtra("content_id", currentContent.getId());

            context.startActivity(intent, options.toBundle());
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