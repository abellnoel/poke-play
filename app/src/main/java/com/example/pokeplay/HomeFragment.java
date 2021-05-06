package com.example.pokeplay;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@RequiresApi(api = Build.VERSION_CODES.N)
public class HomeFragment extends Fragment {
    Spanned TITLE_TEXT = Html.fromHtml("<font color=#e94337>POKE</font><font color=#ffffff>PLAY</font>", Html.FROM_HTML_MODE_LEGACY);

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Home");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Home");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        v.findViewById(R.id.textViewStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.canvas, new LessonFragment())
                        .addToBackStack("Home")
                        .commit();
            }
        });

        TextView title = v.findViewById(R.id.textViewTitle);
        title.setText(TITLE_TEXT);
        title.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        return v;
    }
}