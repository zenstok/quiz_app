package com.example.quiz_app.complexViews.recyclerViewQuestions;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_app.R;
import com.example.quiz_app.sqlite_db.entities.Question;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionViewHolder> {
    private List<Question> mQuestions;

    public QuestionAdapter(List<Question> questions) {
        mQuestions = questions;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.question_item, viewGroup, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder questionViewHolder, int i) {
        Question currentQuestion = mQuestions.get(i);
        if (currentQuestion != null) {
//                questionViewHolder.getTextViewQuestionIterator().setText(getItemCount());
            if (currentQuestion.getText() != null) {
                questionViewHolder.getTextViewQuestionText().setText(currentQuestion.getText());
            }
        }
    }

    @Override
    public int getItemCount() {
        if(mQuestions != null)
            return mQuestions.size();
        return 0;
    }
}
