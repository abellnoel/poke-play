package com.example.pokeplay;

import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.canvas, new HomeFragment())
                .commit();
    }
    //For pokemon team creation buttons, all same behavior
    public void pokeSelect(View view)
    {
        int slotNum = view.getId();
        ImageButton pokeSlot = findViewById(slotNum);

        //Open pokemon search fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.canvas, new ChooseFragment())
                .addToBackStack("Team")
                .commit();
    }
}