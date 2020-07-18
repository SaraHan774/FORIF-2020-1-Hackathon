package com.forif.honsullife.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.L;
import com.forif.honsullife.R;

import java.util.List;

public class RankingAdapter extends PagerAdapter {

    private Context mContext;
    private List<ViewPagerRanking> mRanking;

    public RankingAdapter(Context mContext, List<ViewPagerRanking> mRanking) {
        this.mContext = mContext;
        this.mRanking = mRanking;
    }

    @Override
    public int getCount() {
        return mRanking.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.rank_top_container,container,false);


        ImageView mImageView;
        TextView mTextView;

        mImageView = v.findViewById(R.id.rank_top_order);
        mTextView = v.findViewById(R.id.rank_top_team_name);

        mImageView.setImageResource(mRanking.get(position).getImg());
        mTextView.setText(mRanking.get(position).getName());

        container.addView(v,0);

        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View v = (View)object;
        container.removeView(v);
    }
}
