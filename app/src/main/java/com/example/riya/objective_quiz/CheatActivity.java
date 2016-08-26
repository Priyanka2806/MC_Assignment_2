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
    private Button mShowAnswerButton;
    private TextView mSolution;
    private TextView mAnswer;

    private Bundle bundle = new Bundle();
    Intent returnIntent = new Intent();
    private String ans = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Log.d(TAG, "Inside Cheat Activity");

        mShowAnswerButton = (Button) findViewById(R.id.ShowAnswerButton);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mAnswer = (TextView) findViewById(R.id.textViewer4);
                mAnswer.setText("ANSWER");

                bundle = getIntent().getExtras();
                int value = -1;
                boolean res = false;
                if(bundle != null) {
                    value = bundle.getInt("numberKey");
                    res = bundle.getBoolean("ansKey");
                }

                String num = Integer.toString(value);
                String primeNotPrime = new String();
                if(res)
                    primeNotPrime = " is a Prime Number.";
                else
                    primeNotPrime = " is Not a Prime Number.";

                ans = num + primeNotPrime;

                mSolution = (TextView) findViewById(R.id.textViewer5);
                mSolution.setText(ans);


                returnIntent.putExtra("result","You have cheated and seen the answer, for this Question!");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();


            }


        });


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


