package com.example.quiz_app.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quiz_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewQuizFragment extends Fragment {
    Button mNextButton;
    EditText mQuizNameInput;
    EditText mQuizTimeInput;
    EditText mQuizScoreInput;
    EditText mQuizAttempsInput;
    EditText mQuizPinInput;

    public NewQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_new_quiz, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mNextButton = getView().findViewById(R.id.next);
        mQuizNameInput = getView().findViewById(R.id.quiz_name_input);
        mQuizTimeInput = getView().findViewById(R.id.quiz_time_input);
        final Activity activity = this.getActivity();
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,"Dubsta 6x6",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
