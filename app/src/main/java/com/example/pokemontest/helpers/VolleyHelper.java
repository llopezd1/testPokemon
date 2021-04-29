package com.example.pokemontest.helpers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokemontest.interfaces.JsonPaeser;

public class VolleyHelper {


    private String URL;
    private JsonPaeser jsonPaeser;
    private Context context;

    public VolleyHelper(String URL, JsonPaeser jsonPaeser, Context context) {
        this.URL = URL;
        this.jsonPaeser = jsonPaeser;
        this.context = context;
    }

    static RequestQueue requestQueue;

    public void ApiGetMethod(){

        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("RESPUESTA: " + response);
                jsonPaeser.dataReciver(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("test", "error :" + error.toString());

            }
        });

        requestQueue.add(stringRequest);

    }


}
