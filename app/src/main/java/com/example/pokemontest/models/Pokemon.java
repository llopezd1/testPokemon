package com.example.pokemontest.models;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokemontest.interfaces.JsonPaeser;
import com.example.pokemontest.Utils.Keys;
import com.example.pokemontest.helpers.VolleyHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon {

    private String name;
    private String img_url;


    public Pokemon() {
        this.name = name;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public static void getPokemon(String text, Context context, Pokemon pokemon, TextView textView, ImageView imageView){

        VolleyHelper volleyHelper = new VolleyHelper(Keys.BASE_URL + text, new JsonPaeser() {
            @Override
            public void dataReciver(String JSON_DATA) {

                ParseJsonPokemon(pokemon, JSON_DATA);
                InsertPokemonData(textView, imageView, pokemon ,context);

            }
        }, context);

        volleyHelper.ApiGetMethod();

    }

    public static void ParseJsonPokemon(Pokemon pokemon, String response){

        try {

            JSONObject json_response = new JSONObject(response);
            String name = json_response.getString(Keys.POKEMON_NAME);

            JSONObject sprities = json_response.getJSONObject(Keys.SPRITES);
            JSONObject others = sprities.getJSONObject(Keys.OTHER);
            JSONObject official_art_work = others.getJSONObject(Keys.OFICIAL_ARTWORK);

            String url = official_art_work.getString(Keys.IMAGE);

            pokemon.setName(name);
            pokemon.setImg_url(url);


        } catch (JSONException e) {

            e.printStackTrace();

        }

    }

    public static void InsertPokemonData(TextView textView, ImageView imageView, Pokemon pokemon, Context context){

        textView.setText(pokemon.getName().toString().toUpperCase());
        Glide.with(context)
                .load(pokemon.getImg_url())
                .into(imageView);

    }

}
