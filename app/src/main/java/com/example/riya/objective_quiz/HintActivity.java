package com.example.riya.objective_quiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private static final String TAG = "HintActivity";
    private static final String KEY_VALUE1 = "hint1";
    private static final String KEY_VALUE2 = "hint2";
    private Button mShowHintButton;
    private Button mBackButton;
    private TextView mDesciption;
    private TextView mHint;

    Intent returnIntent = new Intent();
    private String description = new String();
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        Log.d(TAG, "Inside Hint Activity");

        //Button to see hint for answering the ques.
        mShowHintButton = (Button) findViewById(R.id.ShowHintButton);
        mShowHintButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                flag = 1;
                mHint = (TextView) findViewById(R.id.textViewer1);
                mHint.setText("HINT");

                description = "\nA number, greater than 1 is called a Prime No., if it has only 2 factors- 1 & the number itself."
                        + "Suppose A is given number.\n" +
                        "1: Find a whole number(K) nearly greater than the square root of A. " +
                        "\n2: Test whether A is divisible by any prime number less than K. If no, A is a prime number, else No.";

                mDesciption = (TextView) findViewById(R.id.textViewer2);
                mDesciption.setText(description);



            }
        });

        //For getting back to QuizActivity
        mBackButton = (Button) findViewById(R.id.BackButton1);
        mBackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(flag == 1) {
                    returnIntent.putExtra("result", "You have taken Hint, for this Question!");
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                else{
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                }
            }
        });

        if(savedInstanceState != null){
            CharSequence s1 = savedInstanceState.getCharSequence(KEY_VALUE1);
            CharSequence s2 = savedInstanceState.getCharSequence(KEY_VALUE2);

            mHint = (TextView) findViewById(R.id.textViewer1);
            mHint.setText(s2);
            mDesciption = (TextView) findViewById(R.id.textViewer2);
            mDesciption.setText(s1);

        }

    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
        final TextView saveHint1 = (TextView) findViewById(R.id.textViewer2);
        final TextView saveHint2 = (TextView) findViewById(R.id.textViewer1);
        CharSequence hint1 = saveHint1.getText();
        CharSequence hint2 = saveHint2.getText();
        savedInstanceState.putCharSequence(KEY_VALUE1, hint1);
        savedInstanceState.putCharSequence(KEY_VALUE2, hint2);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");

    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnResume");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }
}
