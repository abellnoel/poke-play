package com.example.pokeplay;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TypeFragment extends Fragment {
    int page;
    String lessonPages[];

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
        TextView currentText = v.findViewById(R.id.textViewBody);

        lessonPages = new String[]{getString(R.string.lesson_type1),
                getString(R.string.lesson_type2),
                getString(R.string.lesson_type3),
                getString(R.string.lesson_type4),
                getString(R.string.lesson_type5)};
        page = 0;
        v.findViewById(R.id.buttonBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //prevent decrementing page below 0
                if (page > 0) {
                    page--;
                }
                else { //pressing back on page 0
                    getFragmentManager().beginTransaction()
                            .replace(R.id.canvas, new LessonFragment())
                            .commit();
                }
                currentText.setText(lessonPages[page]);
            }
        });
        v.findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //prevent incrementing page over page length
                if (page < lessonPages.length - 1) {
                    page++;
                }
                else { //pressing
                    getFragmentManager().beginTransaction()
                            .replace(R.id.canvas, new TypeQuizFragment())
                            .commit();
                }
                currentText.setText(lessonPages[page]);
            }
        });
        return v;
    }
}