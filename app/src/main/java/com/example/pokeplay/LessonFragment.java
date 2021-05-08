package com.example.pokeplay;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LessonFragment extends Fragment {
    public LessonFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Lessons");

        //Set transitions
        TransitionInflater tInflater = TransitionInflater.from(requireContext());
        setEnterTransition(tInflater.inflateTransition(R.transition.slide_in));
        setExitTransition(tInflater.inflateTransition(R.transition.fade));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Lessons");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lesson, container, false);

        v.findViewById(R.id.textViewType).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        //.addToBackStack("Lessons")
                        .replace(R.id.canvas, new TypeFragment())
                        .commit();
            }
        });

        v.findViewById(R.id.textViewAbility).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        //.addToBackStack("Lessons")
                        .replace(R.id.canvas, new AbilityFragment())
                        .commit();
            }
        });

        //Saving progress
        if (MainActivity.completed[0] == 1) {
            v.findViewById(R.id.textViewType).setBackgroundColor(Color.GREEN);
        }
        if (MainActivity.completed[1] == 1) {
            v.findViewById(R.id.textViewAbility).setBackgroundColor(Color.GREEN);
        }
        if (MainActivity.completed[0] == 2) {
            v.findViewById(R.id.textViewType).setBackgroundColor(Color.RED);
        }
        if (MainActivity.completed[1] == 2) {
            v.findViewById(R.id.textViewAbility).setBackgroundColor(Color.RED);
        }
        return v;
    }
}