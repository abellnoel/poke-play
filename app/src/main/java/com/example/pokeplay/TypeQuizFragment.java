package com.example.pokeplay;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class TypeQuizFragment extends Fragment {
    TextView ansA, ansB, ansC, ansD, Q;
    Button a, b, c, d, next;

    public TypeQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_type_quiz, container, false);

        //create questions
        ArrayList<Question> qs = new ArrayList<>();
        Question q1 = new Question("Which type below is fire super effective against?", "Water", "Grass","Electric", "Ground", 1);
        Question q2 = new Question("Which type below is water super effective against?", "Water", "Electric","Grass", "Fire", 2);
        qs.add(q1);
        qs.add(q2);

        //get views
        Q = v.findViewById(R.id.textViewQ);
        ansA = v.findViewById(R.id.ansA);
        ansB = v.findViewById(R.id.ansB);
        ansC = v.findViewById(R.id.ansC);
        ansD = v.findViewById(R.id.ansD);
        a = v.findViewById(R.id.a);
        b = v.findViewById(R.id.b);
        c = v.findViewById(R.id.c);
        d = v.findViewById(R.id.d);
        next = v.findViewById(R.id.buttonNextQ);
        next.setVisibility(View.GONE);


        //set view based on question
        final int[] prog = {0};
        final Question[] curr = {qs.get(prog[0])};
        final int[] finalCorrectID = {setQuestion(curr[0])};

        a.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (finalCorrectID[0] == R.id.a) {
                    next.setVisibility(View.VISIBLE);
                    a.setBackgroundColor(Color.GREEN);
                }
                else {
                    a.setBackgroundColor(Color.RED);
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (finalCorrectID[0] == R.id.b) {
                    next.setVisibility(View.VISIBLE);
                    b.setBackgroundColor(Color.GREEN);
                }
                else {
                    b.setBackgroundColor(Color.RED);
                }
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (finalCorrectID[0] == R.id.c) {
                    next.setVisibility(View.VISIBLE);
                    c.setBackgroundColor(Color.GREEN);
                }
                else {
                    c.setBackgroundColor(Color.RED);
                }
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (finalCorrectID[0] == R.id.d) {
                    d.setBackgroundColor(Color.GREEN);
                    next.setVisibility(View.VISIBLE);
                }
                else {
                    d.setBackgroundColor(Color.RED);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.setVisibility(View.GONE);
                if (prog[0] < qs.size() - 1) {
                    prog[0]++;
                    curr[0] = qs.get(prog[0]);
                    finalCorrectID[0] = setQuestion(curr[0]);
                    setQuestion(curr[0]);
                }
                else {
                    next.setText("Finish!");
                    getFragmentManager().beginTransaction()
                            .replace(R.id.canvas, new LessonFragment())
                            .commit();
                }
            }
        });
        return v;
    }

    public int setQuestion(Question curr) {
        //set view based on question
        a.setBackgroundColor(a.getContext().getResources().getColor(R.color.buttonBackground));
        b.setBackgroundColor(a.getContext().getResources().getColor(R.color.buttonBackground));
        c.setBackgroundColor(a.getContext().getResources().getColor(R.color.buttonBackground));
        d.setBackgroundColor(a.getContext().getResources().getColor(R.color.buttonBackground));
        Q.setText(curr.question);
        ansA.setText(curr.a);
        ansB.setText(curr.b);
        ansC.setText(curr.c);
        ansD.setText(curr.d);

        int correctID = 0;
        switch(curr.correct) {
            case 0:
                correctID = R.id.a;
                break;
            case 1:
                correctID = R.id.b;
                break;
            case 2:
                correctID = R.id.c;
                break;
            case 3:
                correctID = R.id.d;
                break;
        }
        return correctID;
    }
}