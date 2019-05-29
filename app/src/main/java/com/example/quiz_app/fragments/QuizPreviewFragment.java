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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_app.MainActivity;
import com.example.quiz_app.R;
import com.example.quiz_app.complexViews.recyclerViewQuestions.QuestionAdapter;
import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;
import com.example.quiz_app.sqlite_db.entities.Question;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizPreviewFragment extends Fragment {
    Button mAddNewQuestion;
    Button mSaveQuiz;
    RecyclerView mRecylerViewQuestions;
    NewQuizInstance nqi;
    List<Question> mQuestions;

    private class DbOperations extends AsyncTask<Void, Void, NewQuizInstance> {
        @Override
        protected NewQuizInstance doInBackground(Void... voids) {
            MainActivity mainActivity = (MainActivity) getActivity();
            return mainActivity.databaseCreator.getDatabase().newQuizInstanceDao().getAllQuizInstances().get(0);
        }

        @Override
        protected void onPostExecute(NewQuizInstance newQuizInstance) {
            nqi = newQuizInstance;
            MainActivity mainActivity = (MainActivity) getActivity();
            ((TextView) mainActivity.findViewById(R.id.quiz_name_val)).setText(newQuizInstance.getName());
            ((TextView) mainActivity.findViewById(R.id.time_val)).setText(String.valueOf(newQuizInstance.getTime()));
            ((TextView) mainActivity.findViewById(R.id.score_val)).setText(String.valueOf(newQuizInstance.getScore()));
            ((TextView) mainActivity.findViewById(R.id.attemps_val)).setText(String.valueOf(newQuizInstance.getAttemps()));
            ((TextView) mainActivity.findViewById(R.id.pin_val)).setText(newQuizInstance.getPin());
        }
    }

    private class SetQuestions extends AsyncTask<Void, Void, List<Question>> {
        @Override
        protected List<Question> doInBackground(Void... voids) {
            MainActivity mainActivity = (MainActivity) getActivity();
            return mainActivity.databaseCreator.getDatabase().questionDao().findQuestionsForQuiz(1);
        }

        @Override
        protected void onPostExecute(List<Question> questions) {
            mQuestions = questions;
            setLayoutManager();
            setAdapter();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        DbOperations putQuizDetailInView = new DbOperations();
        putQuizDetailInView.execute();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddNewQuestion = getView().findViewById(R.id.add_new_question);
        mRecylerViewQuestions = getView().findViewById(R.id.recycler_view_questions);
        mSaveQuiz = getView().findViewById(R.id.save);

        SetQuestions setQuestions = new SetQuestions();
        setQuestions.execute();

        mAddNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddQuestionFragment();
            }
        });

        mSaveQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestions.size() != 0) {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    db.collection("quizes")
                            .add(nqi)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("FIREBASE APP", "DocumentSnapshot added with ID: " + documentReference.getId());
                                    for (Question question : mQuestions) {
                                        documentReference.collection("questions").add(question);
                                    }

                                    deleteQuizPreviewFromDb();
                                    goToQuizesFragment();
                                    Toast.makeText(getContext(), getString(R.string.quiz_created_successfully), Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("FIREBASE APP", "Error adding document", e);
                                }
                            });

                } else {
                    Toast.makeText(getContext(), getString(R.string.no_questions), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToAddQuestionFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fluid_container, new AddQuestionFragment());
        transaction.commit();
    }

    private void goToQuizesFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fluid_container, new QuizesFragment());
        transaction.commit();
    }

    private void setLayoutManager() {
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getActivity());
        mRecylerViewQuestions.setLayoutManager(layoutManager);
    }

    private void setAdapter() {
        QuestionAdapter questionAdapter = new QuestionAdapter(mQuestions);
        mRecylerViewQuestions.setAdapter(questionAdapter);
    }

    @SuppressLint("StaticFieldLeak")
    private void deleteQuizPreviewFromDb() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.databaseCreator.getDatabase().newQuizInstanceDao().deleteNewQuizInstance(nqi);
                return null;
            }

        }.execute();
    }

}
