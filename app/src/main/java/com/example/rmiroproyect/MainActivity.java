package com.example.rmiroproyect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgUsuarioApp;
    TextView txtId, txtUsuario, txtpassword, txtCorreo, txtCreadoApp, txtActualizadoApp;
    private RequestQueue requestQueue;
    private VolleyS volleyS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volleyS = VolleyS.getInstance(this.getApplicationContext());
        requestQueue = volleyS.getRequestQueue();

        imgUsuarioApp = findViewById(R.id.imgUsuarioApp);
        txtId = findViewById(R.id.txtIdApp);
        txtUsuario = findViewById(R.id.txtUsuarioApp);
        txtpassword = findViewById(R.id.txtContra);
        txtCorreo = findViewById(R.id.txtCorreoApp);

        Bundle extra = getIntent().getExtras();
        String bCorreo = extra.getString("email");

        String url = "http://192.168.1.71:8000/api/perfil";
        Toast.makeText(getApplicationContext(),"buscando info",Toast.LENGTH_LONG).show();

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject arreglo = response.getJSONObject ("Users");
                    Gson gson = new Gson();
                    txtId.setText(arreglo.getString("ID"));
                    txtUsuario.setText(arreglo.getString("Usuario"));
                    txtCorreo.setText( arreglo.getString("Correo"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
        txtId.setText("ID");
        txtUsuario.setText("Usuariosss");
        txtCorreo.setText(bCorreo);

        findViewById(R.id.btnSalirApp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        txtId.setText("ID:");
        txtUsuario.setText("Usuario");
        txtpassword.setText("Contraseña: ");
        txtCorreo.setText("Correo:");
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}