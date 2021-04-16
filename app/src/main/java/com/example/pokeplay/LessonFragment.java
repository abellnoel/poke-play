package com.example.pokeplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
                        .addToBackStack("Lessons")
                        .replace(R.id.canvas, new TypeFragment())
                        .commit();
            }
        });

        return v;
    }
}