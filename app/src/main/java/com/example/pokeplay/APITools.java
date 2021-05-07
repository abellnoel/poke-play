package com.example.pokeplay;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class APITools extends AppCompatActivity {
    private RequestQueue queue = Volley.newRequestQueue(this);
    private String baseUrl = "https://pokeapi.co/api/v2";

    private JSONObject getPokemon(String pokemonName) {
        String pokemonUrl = "/pokemon/";
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

    private JSONObject getAbility(String abilityName) {
        String abilityUrl = "/ability/";
        String url = baseUrl + abilityUrl + abilityName;
        final JSONObject[] ability = new JSONObject[1];

        StringRequest abilityRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ability[0] = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("Something went wrong while retrieving an ability.");
            }
        });

        queue.add(abilityRequest);

        return ability[0];
    }

    private JSONObject getMove(String moveName) {
        String moveUrl = "/move/";
        String url = baseUrl + moveUrl + moveName;
        final JSONObject[] move = new JSONObject[1];

        StringRequest moveRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            move[0] = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("Something went wrong while retrieving a move.");
            }
        });

        queue.add(moveRequest);

        return move[0];
    }

    private JSONObject getType(String typeName) {
        String typeUrl = "/type/";
        String url = baseUrl + typeUrl + typeName;
        final JSONObject[] type = new JSONObject[1];

        StringRequest typeRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            type[0] = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.print("Something went wrong while retrieving a type.");
            }
        });

        queue.add(typeRequest);

        return type[0];
    }
}
