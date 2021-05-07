package com.example.pokeplay;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class CreateTeamFragment extends Fragment {
    Pokemon[] team;

    public CreateTeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set transitions
        TransitionInflater tInflater = TransitionInflater.from(requireContext());
        setEnterTransition(tInflater.inflateTransition(R.transition.slide_in));
        setExitTransition(tInflater.inflateTransition(R.transition.fade));

        team = new Pokemon[6];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_team, container, false);

        v.findViewById(R.id.buttonCreateTeam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Save team data to database

                Toast.makeText(getActivity(), "Team created!", Toast.LENGTH_SHORT).show();
                //Back to teams page
                getFragmentManager().popBackStack();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        for (int i = 0; i < 6; i++) {
            if (team[i] != null) {
                int id = 0;
                switch (i) {
                    case 0:
                        id = R.id.poke1;
                        break;
                    case 1:
                        id = R.id.poke2;
                        break;
                    case 2:
                        id = R.id.poke3;
                        break;
                    case 3:
                        id = R.id.poke4;
                        break;
                    case 4:
                        id = R.id.poke5;
                        break;
                    case 5:
                        id = R.id.poke6;
                        break;
                }
                ImageButton b = getView().findViewById(id);
                Picasso.get().load(team[i].iconString).into(b);
            }
        }
    }

    //FOR DATA PASSING
    public void updateTeam(Pokemon pokemon, int slot) {
        switch(slot) {
            case R.id.poke1:
                team[0] = pokemon;
                break;
            case R.id.poke2:
                team[1] = pokemon;
                break;
            case R.id.poke3:
                team[2] = pokemon;
                break;
            case R.id.poke4:
                team[3] = pokemon;
                break;
            case R.id.poke5:
                team[4] = pokemon;
                break;
            case R.id.poke6:
                team[5] = pokemon;
                break;
        }
        //LOG PRINT TEAM
        String pokeString = "";
        for (Pokemon p : team) {
            if (p != null) {
                pokeString += p.getName() + " ";
            }
        }
        Log.d("LOG", "updateTeam: " + pokeString);
    }

    public CreateTeamFragmentListener listener;
    public interface CreateTeamFragmentListener {
        void onSlotSent(int slot);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateTeamFragmentListener) {
            listener = (CreateTeamFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CreateTeamFragmentListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}