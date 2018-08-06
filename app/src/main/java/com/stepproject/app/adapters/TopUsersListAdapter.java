package com.stepproject.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stepproject.app.R;
import com.stepproject.app.models.Scores;
import com.stepproject.app.models.TopUserScoreModel;
import com.stepproject.app.models.User;
import com.stepproject.app.utils.StaticAccess;

import java.util.List;

/**
 * Created by tajmulalam on 12/16/17.
 */

public class TopUsersListAdapter extends RecyclerView.Adapter<TopUsersListAdapter.TopUsersListViewholder> {
    Context mContext;
    List<TopUserScoreModel> scoresList;

    public TopUsersListAdapter(Context mContext, List<TopUserScoreModel> scoresList) {
        this.scoresList = scoresList;
        this.mContext = mContext;
    }

    @Override
    public TopUsersListViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = parent.inflate(mContext, R.layout.log_list_row, null);
        TopUsersListViewholder topUsersListViewholder = new TopUsersListViewholder(view);
        return topUsersListViewholder;
    }

    @Override
    public void onBindViewHolder(TopUsersListViewholder holder, final int position) {

        holder.tvName.setText(scoresList.get(position).getUser().getFirstName() + " " + scoresList.get(position).getUser().getLastName());
        holder.tvDescription.setVisibility(View.GONE);
        holder.tvStepsCount.setText(scoresList.get(position).getScore().getSteps() + " steps");

    }


    @Override
    public int getItemCount() {
        return scoresList.size() > 0 ? scoresList.size() : 0;
    }

    public void updateData(List<TopUserScoreModel> topUsers) {
        this.scoresList.clear();
        this.scoresList.addAll(topUsers);
        notifyDataSetChanged();
    }

    public class TopUsersListViewholder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDescription, tvStepsCount;

        public TopUsersListViewholder(View itemView) {
            super(itemView);
            this.tvName = itemView.findViewById(R.id.tvName);
            this.tvDescription = itemView.findViewById(R.id.tvDescription);
            this.tvStepsCount = itemView.findViewById(R.id.tvStepsCount);
        }
    }

}
