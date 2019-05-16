package com.example.quiz_app.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quiz_app.MainActivity;
import com.example.quiz_app.R;
import com.example.quiz_app.sqlite_db.AppDatabase;
import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizPreviewFragment extends Fragment {

    private class DbOperations extends AsyncTask<Void,Void, NewQuizInstance> {
        @Override
        protected NewQuizInstance doInBackground(Void... voids) {
            MainActivity mainActivity= (MainActivity)getActivity();
            return mainActivity.databaseCreator.getDatabase().newQuizInstanceDao().getAllQuizInstances().get(0);
        }

        @Override
        protected void onPostExecute(NewQuizInstance newQuizInstance) {
            MainActivity mainActivity= (MainActivity)getActivity();
            ((TextView)mainActivity.findViewById(R.id.quiz_content_room)).setText(newQuizInstance.getName());
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
    public void onAttach(Context context) {
        super.onAttach(context);

        DbOperations putQuizDetailInView = new DbOperations();
        putQuizDetailInView.execute();
    }
}
