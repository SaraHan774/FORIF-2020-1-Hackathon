package com.forif.honsullife.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.forif.honsullife.R;

import java.util.ArrayList;

public class RankingRvAdapter extends RecyclerView.Adapter<RankingRvAdapter.RankingViewHolder> {

    private ArrayList<String> stringArrayList ;
    private Context context;
    public RankingRvAdapter(Context context, ArrayList<String> stringArrayList){
        this.context = context;
        this.stringArrayList = stringArrayList;
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ranking_view_holder, parent, false);

        return new RankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        holder.rankOrder.setText(String.format("%d 등", position + 1));
        holder.teamName.setText(stringArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    class RankingViewHolder extends RecyclerView.ViewHolder{

        TextView rankOrder;
        TextView teamName;

        public RankingViewHolder(@NonNull View itemView) {
            super(itemView);

            rankOrder = itemView.findViewById(R.id.rank_order);
            teamName = itemView.findViewById(R.id.rank_team_name);
        }
    }
}
