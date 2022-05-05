package com.example.earthbrowserapp.helpers;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.earthbrowserapp.R;

import java.util.List;

public class PagerAdapter extends android.support.v4.view.PagerAdapter {


//    properties
    private Context context;
    private List<Content> contentList;


//    constructors


    public PagerAdapter(Context context, List<Content> contentList) {
        this.context = context;
        this.contentList = contentList;
    }


    @Override
    public int getCount() {
        return contentList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        View view = LayoutInflater.from(context).inflate(R.layout.activity_content,container,false);
      ImageView imgContent = view.findViewById(R.id.imagecontent);
        TextView titleContent = view.findViewById(R.id.titlecontent);
        TextView messageContent = view.findViewById(R.id.messagecontent);

        Animation upToAnim = AnimationUtils.loadAnimation(context,R.anim.downtoup);

        Glide.with(context).load(contentList.get(position).getContentImage()).into(imgContent);
        titleContent.setText(contentList.get(position).getContentTitle());
        messageContent.setText(contentList.get(position).getContentMessage());
        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);

    }
}
