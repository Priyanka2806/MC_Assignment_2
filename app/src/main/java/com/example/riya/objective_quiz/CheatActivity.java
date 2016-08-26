package com.example.riya.objective_quiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String TAG = "CheatActivity";
    private static final String KEY_VALUE1 = "answer1";
    private static final String KEY_VALUE2 = "answer2";
    private Button mShowAnswerButton;
    private Button mBackButton;
    private TextView mSolution;
    private TextView mAnswer;

    private Bundle bundle = new Bundle();
    Intent returnIntent = new Intent();
    private String ans = new String();
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Log.d(TAG, "Inside Cheat Activity");

        //Button for seeing Answer
        mShowAnswerButton = (Button) findViewById(R.id.ShowAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                flag = 1;

                mAnswer = (TextView) findViewById(R.id.textViewer4);
                mAnswer.setText("ANSWER");

                //Displays the answer to the question according to the random number and solution passed by the QuizActivity
                bundle = getIntent().getExtras();
                int value = -1;
                boolean res = false;
                if (bundle != null) {
                    value = bundle.getInt("numberKey");
                    res = bundle.getBoolean("ansKey");
                }

                String num = Integer.toString(value);
                String primeNotPrime;
                if (res)
                    primeNotPrime = " is a Prime Number.";
                else
                    primeNotPrime = " is Not a Prime Number.";

                ans = num + primeNotPrime;

                mSolution = (TextView) findViewById(R.id.textViewer5);
                mSolution.setText(ans);
            }


        });


        //To get back to the QuizActivity
        mBackButton = (Button) findViewById(R.id.BackButton);
        mBackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(flag == 1) { //To distinguish between whether the user have seen the answer or not.
                    returnIntent.putExtra("result", "You have cheated and seen the answer, for this Question!");
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish(); // In this case result is passed to QuizActivity
                }
                else{ //When no result is passed to QuizActivity
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();
                }
            }
        });

        //For restoration of state while orientation change
        if(savedInstanceState != null){
            CharSequence s1 = savedInstanceState.getCharSequence(KEY_VALUE1);
            CharSequence s2 = savedInstanceState.getCharSequence(KEY_VALUE2);

            mAnswer = (TextView) findViewById(R.id.textViewer1);
            mAnswer.setText(s1);
            mSolution = (TextView) findViewById(R.id.textViewer2);
            mSolution.setText(s2);

        }


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
        final TextView saveAns1 = (TextView) findViewById(R.id.textViewer4);
        final TextView saveAns2 = (TextView) findViewById(R.id.textViewer5);
        CharSequence ans1 = saveAns1.getText();
        CharSequence ans2 = saveAns2.getText();
        savedInstanceState.putCharSequence(KEY_VALUE1, ans1);
        savedInstanceState.putCharSequence(KEY_VALUE2, ans2);
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


