package com.example.quiz_app.complexViews.recyclerViewQuizes;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import com.example.quiz_app.R;

public class QuizViewHolder extends ViewHolder {
    private TextView mTextViewQuizName;

    public QuizViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewQuizName = itemView.findViewById(R.id.quiz_name_db);
    }

    public TextView getTextViewQuizName() {
        return mTextViewQuizName;
    }

}
