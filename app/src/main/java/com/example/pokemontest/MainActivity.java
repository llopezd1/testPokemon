package com.example.pokemontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokemontest.Utils.Keys;
import com.example.pokemontest.models.Pokemon;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle("Encuentra tu Pokemon!");

        //Inicializo los componentes de la interfaz
        ImageButton search_button = findViewById(R.id.search_button);
        ImageView thumb = findViewById(R.id.pokemon_thumb);
        EditText search_text = findViewById(R.id.edit_text_search_pokemon);
        TextView txt_name = findViewById(R.id.txt_name);

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pokemon pokemon = new Pokemon();
                String text = search_text.getText().toString();

                Pokemon.getPokemon(text, MainActivity.this, pokemon, txt_name, thumb);

            }
        });


    }
}