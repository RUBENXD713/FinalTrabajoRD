package com.example.rmiroproyect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class PortadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        new CountDownTimer(2000,1000){

            @Override
            public void onTick(long segundo) {

            }

            @Override
            public void onFinish() {
                int pLocalizacion = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
                int pLlamada = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
                int pCamara = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
                int pAlmacen = ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);

                if (pLocalizacion == PackageManager.PERMISSION_GRANTED &&
                        pLlamada == PackageManager.PERMISSION_GRANTED &&
                        pCamara == PackageManager.PERMISSION_GRANTED &&
                        pAlmacen == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(getApplicationContext(), PermisosActivity.class));
                    finish();
                }
            }
        }.start();
    }
}
