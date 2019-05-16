package com.example.quiz_app.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quiz_app.MainActivity;
import com.example.quiz_app.R;
import com.example.quiz_app.sqlite_db.entities.NewQuizInstance;

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
        mQuizScoreInput = getView().findViewById(R.id.quiz_score_input);
        mQuizAttempsInput = getView().findViewById(R.id.quiz_attemps_input);
        mQuizPinInput = getView().findViewById(R.id.quiz_pin_input);

        final Activity activity = this.getActivity();
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid() == true) {
                    NewQuizInstance nqi = new NewQuizInstance(1,mQuizNameInput.getText().toString(),Integer.parseInt(mQuizTimeInput.getText().toString()),
                            Integer.parseInt(mQuizScoreInput.getText().toString()),Integer.parseInt(mQuizAttempsInput.getText().toString()),mQuizPinInput.getText().toString());

                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.databaseCreator.createDb(mainActivity.getApplication(), nqi);

                    goToAddQuestionFragment();
                } else {
                    Toast.makeText(activity, "Please fill in all required fields!", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean isValid() {
                if (mQuizNameInput.getText().toString().length() != 0 && mQuizScoreInput.getText().toString().length() != 0 && mQuizAttempsInput.getText().toString().length() != 0) {
                    return true;
                }
                return false;
            }
        });
    }

    private void goToAddQuestionFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fluid_container, new QuizPreviewFragment());
        transaction.commit();
    }

}
