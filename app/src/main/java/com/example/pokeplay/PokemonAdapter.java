package com.example.pokeplay;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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


public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder>{
    private String[] localDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView icon;

        public ViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.textViewName);
            icon = (ImageView) view.findViewById(R.id.imageViewIcon);

            // Define click listener for the ViewHolder's View
            Log.d("DEB", "Pokemon clicked!");
        }
        public TextView getName() {
            return name;
        }
        public ImageView getIcon() {
            return icon;
        }
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.view_pokemon, viewGroup, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getName().setText(localDataSet[position]);
        viewHolder.getIcon().setImageResource(R.drawable.pokeball);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}
