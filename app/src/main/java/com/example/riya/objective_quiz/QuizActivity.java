package com.example.riya.objective_quiz;
import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;
public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mTextQuestion;
    private Button mHintButton;
    private Button mCheatButton;
    private TextView mHintText;
    private TextView mCheatText;


    private int temp = 0;
    private static final String TAG = "QuizActivity";
    private static final String KEY_VALUE = "randomNumberValue";
    private final Random rand = new Random();
    private int random_num = 0;
    private String result = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "Inside onCreate");

        mHintText = (TextView)findViewById(R.id.textViewer3);
        mCheatText = (TextView)findViewById(R.id.textViewer6);

        //Action Listener attached with the True Button
        mTrueButton = (Button) findViewById(R.id.TrueButton);
        mTrueButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Log.d(TAG, "Clicked True");

                temp = 1; //temp variable is defined to implement functionality - creates a toast 'Question Unanswered'
                // when user tries to pressed Next Button, without answering the current question.
                Context context = getApplicationContext();
                CharSequence text = "";
                boolean value = checkAnswer(random_num);
                if(value)
                {
                    text = "Correct";
                    mTrueButton.setTextColor(Color.GREEN); //Sets the color of text on the True button, as green, since the answer is correct.
                }
                else {
                    text = "Incorrect";
                    mTrueButton.setTextColor(Color.RED);
                }
                int duration = Toast.LENGTH_SHORT;

                //To create toast, according to the answer, when True Button is clicked.
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        //Action Listener attached with the False Button
        mFalseButton = (Button) findViewById(R.id.FalseButton);
        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Log.d(TAG, "Clicked False");
                temp = 1;
                Context context = getApplicationContext();
                CharSequence text = "";
                boolean value = checkAnswer(random_num);
                if(!value)
                {
                    text = "Correct";
                    mFalseButton.setTextColor(Color.GREEN);
                }
                else{
                    text = "Incorrect";
                    mFalseButton.setTextColor(Color.RED);
                }

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });


        //Action Listener attached with the Next Button
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Log.d(TAG, "Clicked Next");
                mFalseButton.setTextColor(Color.BLACK);
                mTrueButton.setTextColor(Color.BLACK);

                if(temp == 0)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Question Unanswered";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    mHintText.setText("");
                    mCheatText.setText("");
                    temp = 0;
                    random_num = rand.nextInt(1000) + 1;
                    generatingQues();
                }
            }
        });

        //Action Listener for Hint Button
        mHintButton = (Button) findViewById(R.id.hint_button);
        mHintButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(getApplicationContext(),HintActivity.class), 1);
            }
        });

        //Action Listener for Cheat Button
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),CheatActivity.class);
                intent.putExtra("numberKey", random_num);
                boolean ans = checkAnswer(random_num);
                intent.putExtra("ansKey", ans);
                //Put random number to next Intent
                startActivityForResult(intent, 2);
            }
        });

        //To check whether the activity is created (for the first time) or is being restored.
        if(savedInstanceState!=null)
        {
            //If the activity is being restored, the saved state of activity is taken to recreated it.
            random_num = savedInstanceState.getInt(KEY_VALUE);
            Log.d(TAG, "Restoring the Activity");

        }
        else{
            random_num = rand.nextInt(1000) + 1;
        }


        generatingQues();
    }

    //Function to generate questions with random numbers.
    private void generatingQues(){
        String num = Integer.toString(random_num);
        String ques = " is a Prime Number?";
        String question = num + ques;
        mTextQuestion=(TextView)findViewById(R.id.textViewer);
        mTextQuestion.setText(question);
    }

    //Function to check whether the Answer Clicked (Button pressed) by the user, matches the correct answer..
    private boolean checkAnswer(int numberToBeChecked){
        Log.d(TAG, "Inside CheckAnswer");
        int i,var1=0,flag=0;
        boolean result = true;
        var1=numberToBeChecked/2;
        for(i=2;i<=var1;i++){
            if(numberToBeChecked%i==0){
                result = false;
                flag=1;
                break;
            }
        }
        if(flag==0)
            result = true;
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                result=data.getStringExtra("result");

                mHintText.setText(result);
            }
        }
        else if (requestCode == 2) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");

                mCheatText.setText(result);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
        savedInstanceState.putInt(KEY_VALUE, random_num);
        //To save current state details of the activity.
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

