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

public class MainActivity extends AppCompatActivity implements ChooseFragment.ChooseFragmentListener, CreateTeamFragment.CreateTeamFragmentListener {
    public static int[] completed = {0, 0};
    private CreateTeamFragment teamFragment;
    private ChooseFragment chooseFragment;

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

        chooseFragment = new ChooseFragment();
        teamFragment = (CreateTeamFragment) getSupportFragmentManager().findFragmentByTag("CreateTeam");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.canvas, chooseFragment)
                .addToBackStack("Team")
                .commit();
        teamFragment.listener.onSlotSent(slotNum);
    }

    //FOR DATA PASSING
    @Override
    public void onPokemonChosen(Pokemon pokemon, int slot) {
        teamFragment.updateTeam(pokemon, slot);
    }
    @Override
    public void onSlotSent(int slot) {
        chooseFragment.setSlot(slot);
    }
}