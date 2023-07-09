package com.example.quiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.quiz.R;
import com.example.quiz.model.Score;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {
    /**
     * Create ViewHolder class to bind list item view
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        public TextView tvUsername;
        public TextView tvScore;
        public TextView tvFullScore;

        public ViewHolder(View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvFullScore = itemView.findViewById(R.id.tvFullScore);
        }
        @Override
        public boolean onLongClick(View view) {
            currentPos = getAdapterPosition(); //key point, record the position here
            return false;
        }
    }

    private List<Score> sListData;   // list of book objects
    private Context mContext;       // activity context
    private int currentPos;         //current selected position.

    public ScoreAdapter(Context context, List<Score> listData){
        sListData = listData;
        mContext = context;
    }

    private Context getmContext(){return mContext;}


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the single item layout
        View view = inflater.inflate(R.layout.userscore_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // bind data to the view holder
        Score s = sListData.get(position);
        holder.tvUsername.setText("Username: "+ s.getUsername());
        holder.tvScore.setText("Score: "+ s.getCorrect());
        holder.tvFullScore.setText("Full Score: "+ s.getFullScore());
    }

    @Override
    public int getItemCount() {
        return sListData.size();
    }
}
