package com.mobarokOP.imageslider;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class ImageSlider extends FrameLayout {

    private ViewPager2 viewPager;
    private Handler slideHandler;
    private Runnable slideRunnable;
    private int slideDelay = 3000; // Default 3 seconds
    private boolean autoSlide = true;
    private int itemScaleType = 1; // Default to FIT_CENTER
    private int itemPadding = 70; // Default to 0dp
    private int itemMargin = 20; // Default to 0dp
    MarginPageTransformer marginPageTransformer;
    public ImageSlider(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public ImageSlider(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        marginPageTransformer = new MarginPageTransformer(itemMargin);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        // Inflate the layout containing ViewPager2
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_image_slider, this, true);
        viewPager = rootView.findViewById(R.id.viewPager);

        slideHandler = new Handler();

        // Handle custom attributes
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageSlider);

            slideDelay = typedArray.getInt(R.styleable.ImageSlider_slideDelay, slideDelay);
            autoSlide = typedArray.getBoolean(R.styleable.ImageSlider_autoSlide, autoSlide);
            itemScaleType = typedArray.getInt(R.styleable.ImageSlider_itemScaleType, itemScaleType);
            itemPadding = typedArray.getDimensionPixelSize(R.styleable.ImageSlider_itemPadding, itemPadding);
            itemMargin = typedArray.getDimensionPixelSize(R.styleable.ImageSlider_itemMargin, itemMargin);
            typedArray.recycle();
        }

        if (autoSlide) {
            startAutoSlide();
        }
    }


    public void setItems(Context context, ArrayList<SlideModel> items) {
        SlideAdapter adapter = new SlideAdapter(context, items,itemScaleType);
        viewPager.setAdapter(adapter);


        viewPager.setPadding(itemPadding,0,itemPadding,0);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setClipChildren(false);
        viewPager.setClipToPadding(false);
        viewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(itemMargin));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY( 0.85f + r * 0.15f );
            }
        });

        //binding.viewPager.setCurrentItem(3,true);
        viewPager.setPageTransformer(compositePageTransformer);
    }


    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        SlideAdapter adapter = (SlideAdapter) viewPager.getAdapter();
        if (adapter != null) {
            adapter.setOnItemClickListener(onItemClickListener);
        }
    }

    public void enableAutoSlide(boolean enable) {
        autoSlide = enable;
        if (enable) {
            startAutoSlide();
        } else {
            stopAutoSlide();
        }
    }

    public void setItemScaleType(int scaleType) {
        this.itemScaleType = scaleType;
    }

    public int getItemScaleType() {
        return itemScaleType;
    }

    public void setSlideDelay(int delayMillis) {
        this.slideDelay = delayMillis;
    }


    public int getSlideDelay() {
        return slideDelay;
    }

    public boolean isAutoSlide() {
        return autoSlide;
    }

    public void setAutoSlide(boolean autoSlide) {
        this.autoSlide = autoSlide;
    }

    public int getItemPadding() {
        return itemPadding;
    }

    public void setItemPadding(int itemPadding) {
        this.itemPadding = itemPadding;
        viewPager.setPadding(itemPadding, itemPadding, itemPadding, itemPadding);
    }

    public int getItemMargin() {
        return itemMargin;
    }

    public void setItemMargin(int itemMargin) {
        this.itemMargin = itemMargin;
        marginPageTransformer = new MarginPageTransformer(itemMargin);
    }

    private void startAutoSlide() {
        if (slideRunnable == null) {
            slideRunnable = new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getAdapter() == null || viewPager.getAdapter().getItemCount() == 0) {
                        return;
                    }
                    int nextItem = (viewPager.getCurrentItem() + 1) % viewPager.getAdapter().getItemCount();
                    viewPager.setCurrentItem(nextItem, true);
                    slideHandler.postDelayed(this, slideDelay);
                }
            };
        }
        slideHandler.postDelayed(slideRunnable, slideDelay);
    }

    private void stopAutoSlide() {
        if (slideRunnable != null) {
            slideHandler.removeCallbacks(slideRunnable);
        }
    }
}
