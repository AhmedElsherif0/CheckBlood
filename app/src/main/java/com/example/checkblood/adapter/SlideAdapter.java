package com.example.checkblood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.example.checkblood.R;
import java.util.ArrayList;
import java.util.List;


public class SlideAdapter extends PagerAdapter {


    Context context;

    private List<Integer> images = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public SlideAdapter(Context context) {
        images.add(R.drawable.slide1);
        images.add(R.drawable.slide2);
        this.context = context;

    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View itemView = mLayoutInflater.inflate(R.layout.item_slide, container, false);

            ImageView imageView = itemView.findViewById(R.id.slidefragment_image_slide);
            imageView.setImageResource(images.get(position));

            container.addView(itemView);
            return itemView;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
