package com.example.rmiroproyect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Correo;
    EditText password;
    private RequestQueue requestQueue;
    private VolleyS volleyS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        volleyS = VolleyS.getInstance(this.getApplicationContext());
        requestQueue = volleyS.getRequestQueue();

        Correo = findViewById(R.id.txtCorreo);
        password = findViewById(R.id.txtContra);

        findViewById(R.id.btnIniciar).setOnClickListener(this);
        findViewById(R.id.btnRegistrar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIniciar:
                String url = "http://192.168.1.71:8000/api/login";

                final JSONObject datos = new JSONObject();
                try {
                    datos.put("email", Correo.getText());
                    datos.put("password", password.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, datos, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), response.toString(),Toast.LENGTH_LONG).show();
                        Log.i( "buscando", "hola");


                        Intent logeo = new Intent(getApplicationContext(), MainActivity.class);
                        logeo.putExtra("email", Correo.getText());
                        startActivity(logeo);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                requestQueue.add(request);

                break;
            case R.id.btnRegistrar:
                startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
                break;
        }

    }

}