package com.example.quiz_app.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quiz_app.R;
import com.example.quiz_app.complexViews.recyclerViewQuizes.QuizAdapter;
import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizesFragment extends Fragment {
    List<NewQuizInstance> mQuizzes;
    RecyclerView mRecyclerViewQuizzes;


    public QuizesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quizes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerViewQuizzes = getView().findViewById(R.id.recycler_view_quizzes);

        setQuizzes();
    }

    private void setQuizzes() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("quizes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    mQuizzes = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        NewQuizInstance temp = new NewQuizInstance(1,document.getData().get("name").toString(),10,10,10,"10");
                        mQuizzes.add(temp);
                    }
                    setLayoutManager();
                    setAdapter();
                } else {
                    Log.d("FIREBASE APP", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private void setLayoutManager() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewQuizzes.setLayoutManager(layoutManager);
    }

    private void setAdapter() {
        QuizAdapter quizAdapter = new QuizAdapter(mQuizzes);
        mRecyclerViewQuizzes.setAdapter(quizAdapter);

    }
}
