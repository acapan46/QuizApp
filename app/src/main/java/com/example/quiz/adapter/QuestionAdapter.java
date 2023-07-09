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
    class ViewHolder extends RecyclerView.ViewHolder{

//        public TextView tvOption1;
//        public TextView tvOption2;
//        public TextView tvOption3;
//        public TextView tvOption4;
//        public TextView tvAnswer;
        public TextView tvContent;
//        public Button btnDel;

        public ViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
     //       btnDel = (Button) itemView.findViewById(R.id.btnDel);
        }
    }

    private List<qQuestion> mListData;   // list of book objects
    private Context mContext;       // activity context

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
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }


}