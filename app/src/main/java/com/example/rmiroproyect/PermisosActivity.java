package com.example.rmiroproyect;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermisosActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvPermiso;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permisos);

        findViewById(R.id.btnLogin).setOnClickListener(this);

        rvPermiso = findViewById(R.id.rvPermisos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPermiso.setLayoutManager(layoutManager);


        final List<Permiso> ListaPermisos = new ArrayList<>();
        ListaPermisos.add(new Permiso("De Localizacion", Manifest.permission.ACCESS_FINE_LOCATION));
        ListaPermisos.add(new Permiso("De Llamadas", Manifest.permission.CALL_PHONE));
        ListaPermisos.add(new Permiso("De Camara", Manifest.permission.CAMERA));
        ListaPermisos.add(new Permiso("De Almacenamiento", Manifest.permission.READ_EXTERNAL_STORAGE));


        final AdaptadorPermiso Permisos = new AdaptadorPermiso(ListaPermisos, this);
        rvPermiso.setAdapter(Permisos);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.i("permisos_nombre", Arrays.deepToString(permissions));
        Log.i("permisos_acceso", Arrays.toString(grantResults));

        if (requestCode==26){
            if (permissions.length>=1){
                int acceso=-1;
                for (int permiso:grantResults){
                    permiso = acceso;
                    if (permiso == PackageManager.PERMISSION_DENIED)
                        break;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin){
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }
}