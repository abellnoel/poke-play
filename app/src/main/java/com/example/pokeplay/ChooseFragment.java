package com.example.pokeplay;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChooseFragment extends Fragment {
    public ChooseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set transitions
        TransitionInflater tInflater = TransitionInflater.from(requireContext());
        setEnterTransition(tInflater.inflateTransition(R.transition.slide_in));
        setExitTransition(tInflater.inflateTransition(R.transition.fade));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        View v = inflater.inflate(R.layout.fragment_choose_list, container, false);
        TextView pokeName = v.findViewById(R.id.textViewPokeName);
        TextView searchBar = v.findViewById(R.id.editTextSearch);
        ImageView picture = v.findViewById(R.id.imageViewPicture);
        TextView typesText = v.findViewById(R.id.textViewTypes);

        //search for pokemon
        final Pokemon[] chosenPokemon = new Pokemon[1];
        v.findViewById(R.id.buttonSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pokemonUrl = "/pokemon/";
                String baseUrl = "https://pokeapi.co/api/v2";
                String pokemonName = searchBar.getText().toString().toLowerCase();
                String url = baseUrl + pokemonUrl + pokemonName;

                StringRequest pokemonRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject pokemon = new JSONObject(response);
                                    //show image
                                    JSONObject sprites = pokemon.getJSONObject("sprites");
                                    String url = sprites.getJSONObject("other")
                                            .getJSONObject("official-artwork")
                                            .getString("front_default");
                                    Picasso.get().load(url).into(picture);
                                    pokeName.setText(pokemonName.toUpperCase());

                                    //show types
                                    String typeString = "";
                                    JSONArray types = pokemon.getJSONArray("types");
                                    for (int i = 0; i < types.length(); i++) {
                                        JSONObject slot = types.getJSONObject(i);
                                        JSONObject type = slot.getJSONObject("type");
                                        String typeName = type.getString("name");
                                        typeString += typeName.toUpperCase();
                                        //make comma separated if not last type
                                        if (i != types.length() - 1) {
                                            typeString += ", ";
                                        }
                                    }
                                    //change text view to typeString
                                    typesText.setText(typeString);

                                    //get pokemon url
                                    String pokeUrl = "https://pokeapi.co/api/v2/pokemon/" + pokemonName;
                                    //If all retrievals work, create pokemon object
                                    chosenPokemon[0] = new Pokemon(pokemonName, typeString, url, pokeUrl);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.print("Something went wrong while retrieving a Pokemon.");
                    }
                });
                queue.add(pokemonRequest);
                //hide keyboard when search button is presed
                dismissKeyboard(getActivity());
            }
        });

        //Confirm selection
        v.findViewById(R.id.buttonChoose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pass pokemon selection back to CreateTeamFragment
                if (chosenPokemon[0] != null) {
                    listener.onPokemonChosen(chosenPokemon[0], slot);
                    getFragmentManager().popBackStack();
                }
                else {
                    Toast.makeText(getActivity(), "No Pokemon chosen, please use the search bar above.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }

    //DATA PASSING
    int slot;
    public void setSlot(int slot) {
        this.slot = slot;
    }

    private ChooseFragmentListener listener;
    public interface ChooseFragmentListener {
        void onPokemonChosen(Pokemon pokemon, int slot);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ChooseFragmentListener) {
            listener = (ChooseFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ChooseFragmentListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    //hide keyboard on search
    public void dismissKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus())
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }
}