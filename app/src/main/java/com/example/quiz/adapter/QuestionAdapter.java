package com.example.quiz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.quiz.R;
import com.example.quiz.model.qQuestion;

import java.text.BreakIterator;
import java.util.List;


public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    /**
     * Create ViewHolder class to bind list item view
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

//        public TextView tvOption1;
//        public TextView tvOption2;
//        public TextView tvOption3;
//        public TextView tvOption4;
        public TextView tvAnswer;
        public TextView tvContent;
//        public Button btnDel;

        public ViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvAnswer = (TextView) itemView.findViewById(R.id.tvAnswer);
     //       btnDel = (Button) itemView.findViewById(R.id.btnDel);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            currentPos = getAdapterPosition(); //key point, record the position here
            return false;        }
    }

    private List<qQuestion> mListData;   // list of book objects
    private Context mContext;       // activity context
    private int currentPos;         //current selected position.

    public QuestionAdapter(Context context, List<qQuestion> listData){
        mListData = listData;
        mContext = context;
    }

    private Context getmContext(){return mContext;}


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the single item layout
        View view = inflater.inflate(R.layout.question_list_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // bind data to the view holder
        qQuestion m = mListData.get(position);
        holder.tvContent.setText(m.getContent());
        holder.tvAnswer.setText(m.getCorrect());

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public qQuestion getSelectedItem() {
        if(currentPos>=0 && mListData!=null && currentPos<mListData.size()) {
            return mListData.get(currentPos);
        }
        return null;
    }
}