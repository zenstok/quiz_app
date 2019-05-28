package com.example.quiz_app.complexViews.recyclerViewQuestions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.example.quiz_app.R;

public class QuestionViewHolder extends ViewHolder {
    private TextView mTextViewQuestionIterator;
    private TextView mTextViewQuestionText;
    private TextView mTextViewQuestionAnswer;

    public QuestionViewHolder(@NonNull View itemView) {
        super(itemView);

        mTextViewQuestionIterator = itemView.findViewById(R.id.question_iterator);
        mTextViewQuestionText = itemView.findViewById(R.id.question_text);
        mTextViewQuestionAnswer = itemView.findViewById(R.id.question_answer);
    }

    public TextView getTextViewQuestionIterator() {
        return mTextViewQuestionIterator;
    }

    public TextView getTextViewQuestionText() {
        return mTextViewQuestionText;
    }

    public TextView getTextViewQuestionAnswer() {
        return mTextViewQuestionAnswer;
    }
}
