package com.mobarokOP.imageslider;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.ViewHolder> {

    Context context;
    private ArrayList<SlideModel> items;
    OnItemClickListener onItemClickListener;
    int itemScaleType;

    public SlideAdapter(Context context, ArrayList<SlideModel> items,@Nullable int itemScaleType) {
        this.context = context;
        this.items = items;
        this.itemScaleType = itemScaleType;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slide, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SlideModel item = items.get(position);
        if (item.imageLink!=null) Glide.with(context).load(item.getImageLink()).into(holder.slideImage);
        else holder.slideImage.setImageResource(item.getImageDrawable());
        if (item.getTitle()!=null){
            holder.contentLayout.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.VISIBLE);
            holder.title.setText(item.getTitle());
        } else {
            holder.contentLayout.setVisibility(View.GONE);
            holder.title.setVisibility(View.GONE);
        }

        if (item.getDescription()!=null) {
            holder.contentLayout.setVisibility(View.VISIBLE);
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(item.getDescription());
        } else {
            holder.contentLayout.setVisibility(View.GONE);
            holder.description.setVisibility(View.GONE);
        }


        if (item.openLink!=null) {
            holder.slideButton.setOnClickListener(view -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(item.getOpenLink()));
                context.startActivity(intent);
            });
        } else {
            holder.slideButton.setOnClickListener(view -> onItemClickListener.onItemClick(position));
        }


        // Set ScaleType dynamically
        switch (itemScaleType) {
            case 1:
                holder.slideImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
            case 2:
                holder.slideImage.setScaleType(ImageView.ScaleType.FIT_START);
                break;
            case 3:
                holder.slideImage.setScaleType(ImageView.ScaleType.FIT_END);
                break;
            case 4:
                holder.slideImage.setScaleType(ImageView.ScaleType.FIT_XY);
                break;
            case 5:
                holder.slideImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            default:
                holder.slideImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView slideButton;
        ImageView slideImage;
        LinearLayout contentLayout;
        TextView title, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            slideButton = itemView.findViewById(R.id.slideButton);
            slideImage = itemView.findViewById(R.id.slideImage);
            contentLayout = itemView.findViewById(R.id.contentLayout);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
