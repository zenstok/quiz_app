package com.example.quiz_app.fragments;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.quiz_app.MainActivity;
import com.example.quiz_app.R;
import com.example.quiz_app.sqlite_db.AppDatabase;
import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;
import com.example.quiz_app.sqlite_db.entities.Question;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddQuestionFragment extends Fragment {
    Button mAddQuestion;
    TextView mQuestionText;

    public AddQuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_question2, container, false);
    }

    @Override
    @SuppressLint("StaticFieldLeak")
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddQuestion = getView().findViewById(R.id.add_question);
        mQuestionText = getView().findViewById(R.id.question_et2);

        mAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        AppDatabase db = ((MainActivity)getActivity()).databaseCreator.getDatabase();
                        db.questionDao().insert(new Question(0,mQuestionText.getText().toString(),1));

                        return null;
                    }
                }.execute();
                goToQuizPreviewFragment();
            }
        });
    }

    private void goToQuizPreviewFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fluid_container, new QuizPreviewFragment());
        transaction.commit();
    }
}
