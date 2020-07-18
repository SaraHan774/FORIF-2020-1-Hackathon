package com.forif.honsullife.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.forif.honsullife.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ContentsViewHolder> {

    private Context mContext;
    private List<Post> mPosts;
    private OnItemClickListener mListener;

    public RvAdapter(Context context, List<Post> mPosts) {
        this.mContext = context;
        this.mPosts = mPosts;
    }

    public class ContentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mThumbnail;
        private TextView mTitle;
        private MaterialCardView mCard;

        public ContentsViewHolder(@NonNull View itemView) {
            super(itemView);
            mThumbnail = itemView.findViewById(R.id.other_team_image_view);
            mTitle = itemView.findViewById(R.id.tv_other_team_post_title);
            mCard = itemView.findViewById(R.id.other_team_post_card);
            mCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                int pos = getAdapterPosition();
                if(pos != RecyclerView.NO_POSITION){
                    mListener.OnItemClickListener(pos);
                }
            }
        }
    }

    @NonNull
    @Override
    public ContentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v =inflater.inflate(R.layout.others_post_view_holder,parent,false);
        return new ContentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentsViewHolder holder, int position) {
        Post postCur = mPosts.get(position);
        holder.mTitle.setText(postCur.getPostName() + "\n"+postCur.getCreatedAt());
        Glide.with(mContext)
                .load(postCur.getPhotoUrl())
                .centerCrop()
                .into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public interface OnItemClickListener{
        void OnItemClickListener(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


}
