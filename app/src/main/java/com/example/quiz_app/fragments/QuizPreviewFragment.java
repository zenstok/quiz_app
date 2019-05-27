package com.example.quiz_app.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizPreviewFragment extends Fragment {
    Button mAddNewQuestion;

    private class DbOperations extends AsyncTask<Void,Void, NewQuizInstance> {
        @Override
        protected NewQuizInstance doInBackground(Void... voids) {
            MainActivity mainActivity= (MainActivity)getActivity();
            return mainActivity.databaseCreator.getDatabase().newQuizInstanceDao().getAllQuizInstances().get(0);
        }

        @Override
        protected void onPostExecute(NewQuizInstance newQuizInstance) {
            MainActivity mainActivity= (MainActivity)getActivity();
            ((TextView)mainActivity.findViewById(R.id.quiz_name_val)).setText(newQuizInstance.getName());
            ((TextView)mainActivity.findViewById(R.id.time_val)).setText(String.valueOf(newQuizInstance.getTime()));
            ((TextView)mainActivity.findViewById(R.id.score_val)).setText(String.valueOf(newQuizInstance.getScore()));
            ((TextView)mainActivity.findViewById(R.id.attemps_val)).setText(String.valueOf(newQuizInstance.getAttemps()));
            ((TextView)mainActivity.findViewById(R.id.pin_val)).setText(newQuizInstance.getPin());
        }
    }

    public QuizPreviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_preview, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        DbOperations putQuizDetailInView = new DbOperations();
//        putQuizDetailInView.execute();
//    }

    @Override
    @SuppressLint("StaticFieldLeak")
    public void onAttach(Context context) {
        super.onAttach(context);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppDatabase db = ((MainActivity)getActivity()).databaseCreator.getDatabase();
                if(db.questionDao().getAllQuestions().size() != 0)
                    Log.e("BANANA", db.questionDao().getAllQuestions().toString());

                //put question to new quiz instance
                return null;
            }
        }.execute();
        DbOperations putQuizDetailInView = new DbOperations();
        putQuizDetailInView.execute();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddNewQuestion = getView().findViewById(R.id.add_new_question);

        mAddNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddQuestionFragment();
            }
        });
    }

    private void goToAddQuestionFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fluid_container, new AddQuestionFragment());
        transaction.commit();
    }
}
