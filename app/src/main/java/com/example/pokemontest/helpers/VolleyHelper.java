package com.example.pokemontest.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

        ProgressDialog progress = ProgressDialog.show(context, "Cargando...",
                "Por Favor Espere !", true);

        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("RESPUESTA: " + response);
                jsonPaeser.dataReciver(response);
                progress.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error.getMessage() == null){

                    Toast.makeText(context, "ERROR 404: POKEMON NO EXISTE", Toast.LENGTH_LONG).show();
                    progress.dismiss();

                }

            }
        });

        requestQueue.add(stringRequest);

    }


}
