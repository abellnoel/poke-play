package com.example.pokeplay;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;

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
        TextView searchBar = v.findViewById(R.id.editTextSearch);
        ImageView picture = v.findViewById(R.id.imageViewPicture);

        //search for pokemon
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
            }
        });


        return v;
    }

    private JSONObject getPokemon(String pokemonName, RequestQueue queue) {
        String pokemonUrl = "/pokemon/";
        String baseUrl = "https://pokeapi.co/api/v2";
        String url = baseUrl + pokemonUrl + pokemonName;
        final JSONObject[] pokemon = new JSONObject[1];

        StringRequest pokemonRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pokemon[0] = new JSONObject(response);
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

        return pokemon[0];
    }
}