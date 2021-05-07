package com.example.pokeplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TypeFragment extends Fragment {
    public TypeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Type Dynamics");

        //Set transitions
        TransitionInflater tInflater = TransitionInflater.from(requireContext());
        setEnterTransition(tInflater.inflateTransition(R.transition.slide_in));
        setExitTransition(tInflater.inflateTransition(R.transition.fade));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Type Dynamics");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_type, container, false);

        return v;
    }
}