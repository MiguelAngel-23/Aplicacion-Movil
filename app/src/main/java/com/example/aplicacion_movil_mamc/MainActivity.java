package com.example.aplicacion_movil_mamc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_toast;
    Button btn_Activity;
    toast toastClass = new toast();
    Button btn_bus;
    EditText editTextWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_toast = findViewById(R.id.btnToast);
        btn_Activity = findViewById(R.id.btnActivity);
        editTextWeb = (EditText) findViewById(R.id.editTextWeb);
        btn_bus = findViewById(R.id.btnBus);

        //Mostrar TOAST
        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastClass.mostrarToast(MainActivity.this);
            }
        });

        //Evento Buscar URL
        btn_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl();
            }
        });

        //Evento Nuevo Activity
        btn_Activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiguienteActivity(v);
            }
        });
    }
    //Siguiente ACtivity
    public void SiguienteActivity(View view) {
        Intent intent = new Intent(this, SiguienteActivity.class);
        startActivity(intent);
    }

    //Buscar Direccion URL
    public void openUrl() {
        String url = editTextWeb.getText().toString();
        if (url != null && !url.isEmpty()) {
            Intent intentWeb = new Intent();
            intentWeb.setAction(Intent.ACTION_VIEW);
            intentWeb.setData(Uri.parse("http://"+url));
            startActivity(intentWeb);
        } else {
            Toast.makeText(MainActivity.this, "Ingresa una Dirección URL a Buscar", Toast.LENGTH_SHORT).show();
        }
    }
}
