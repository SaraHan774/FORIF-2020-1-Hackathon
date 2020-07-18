package com.forif.honsullife.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.forif.honsullife.R;
import com.forif.honsullife.ui.PostDetailActivity;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static com.forif.honsullife.ui.MainActivity.CURRENT_POST;

public class OurRvAdapter extends RecyclerView.Adapter<OurRvAdapter.OurViewHolder>{

    private static final String TAG = "OurRvAdapter";
    private Context context;
    private ArrayList<Post> postArrayList;

    public OurRvAdapter(
            Context context,
            ArrayList<Post> postArrayList
    ){
        this.context = context;
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public OurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_view_holder, parent, false);

        return new OurViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OurViewHolder holder, int position) {
        Post post = postArrayList.get(position);
        if(!post.getPhotoUrl().isEmpty()) {
            Glide.with(context)
                    .load(post.getPhotoUrl())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.ourImage);
        }
        if(!post.getPostName().isEmpty()){
            holder.tvOurTitle.setText(post.getPostName());
        }
        holder.ourTeamPostCard.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostDetailActivity.class);
            intent.putExtra(CURRENT_POST, post);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + postArrayList.size());
        return postArrayList.size();
    }

    static class OurViewHolder extends RecyclerView.ViewHolder{

        TextView tvOurTitle;
        ImageView ourImage;
        MaterialCardView ourTeamPostCard;

        public OurViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOurTitle = itemView.findViewById(R.id.tv_our_team_post_title);
            ourImage = itemView.findViewById(R.id.our_team_image);
            ourTeamPostCard = itemView.findViewById(R.id.our_team_post_card);
        }
    }
}
