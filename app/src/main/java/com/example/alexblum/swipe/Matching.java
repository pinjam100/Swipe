package com.example.alexblum.swipe;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Matching extends FragmentActivity implements View.OnClickListener{
    ViewPager viewPager;
    private Button buttonQ;
    private EditText editTextQ;
    private String question;
    private Integer count;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);
        viewPager = (ViewPager) findViewById(R.id.view_pager);


        buttonQ = (Button) findViewById(R.id.buttonQ);
        editTextQ = (EditText) findViewById(R.id.editTextQ);
        question = editTextQ.getText().toString();
        count = 0;
        buttonQ.setOnClickListener(this);

        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
    }

    @Override
    public void onClick(View v) {
        if (question.equals("")) {

        } else {
            count = count+1;
            question = editTextQ.getText().toString();
            Question Q = new Question(count,question,0);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference dataQuestions = database.getReference("questions");
            DatabaseReference dataNewQuestion = dataQuestions.push();
            dataNewQuestion.setValue(Q);
            Toast.makeText(this, "Your question was posted.", Toast.LENGTH_SHORT).show();
        }

    }
}
