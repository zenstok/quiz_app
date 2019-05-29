package com.example.quiz_app.complexViews.recyclerViewQuizes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_app.R;
import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private List<NewQuizInstance> mQuizes;

    public QuizAdapter(List<NewQuizInstance> quizes) {
        mQuizes = quizes;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quiz_item, viewGroup, false);
        return new QuizViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder quizViewHolder, int i) {
        NewQuizInstance currentQuiz = mQuizes.get(i);
        if (currentQuiz != null) {
                quizViewHolder.getTextViewQuizName().setText(currentQuiz.getName());
        }
    }

    @Override
    public int getItemCount() {
        if(mQuizes != null)
            return mQuizes.size();
        return 0;
    }
}
