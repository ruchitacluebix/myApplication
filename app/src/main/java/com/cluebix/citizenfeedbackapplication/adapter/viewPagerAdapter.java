package com.cluebix.citizenfeedbackapplication.adapter;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cluebix.citizenfeedbackapplication.Constant.Connection;
import com.cluebix.citizenfeedbackapplication.Constant.Constant;
import com.cluebix.citizenfeedbackapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class viewPagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> imageUrlList;
    public static final String SPLASH_IMAGE = Connection.IMAGE_URL ;

    public viewPagerAdapter(Context context, ArrayList imageUrlList) {
        this.context = context;
        this.imageUrlList = imageUrlList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageUrlList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ConstraintLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.slider_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView_slider);
      //  imageView.setImageResource(images[position]);

        String imageUrl = imageUrlList.get(position);

        Picasso.with(context)
                .load(SPLASH_IMAGE +imageUrl)
                .placeholder(R.drawable.equicity_logo) //this is optional the image to display while the url image is downloading
            //    .error(Your Drawable Resource)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(imageView);
        container.addView(itemView);



        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
