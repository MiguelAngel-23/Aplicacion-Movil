package com.example.aplicacion_movil_mamc;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SiguienteActivity extends AppCompatActivity {

    static final int REQUEST_CALL = 1;
    EditText phone;
    Button btn_return;
    Button btn_call;
    Button btn_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.txtphone);
        btn_return = findViewById(R.id.btnreturn);
        btn_call = findViewById(R.id.btnCall);
        btn_contact = findViewById(R.id.btnContact);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            //Metodos acciones a realizar
            public void onClick(View v) {
                MainActivity(v);
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNumber();
            }
        });

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts();
            }
        });
    }

    //Regresar Activity Principal
    public void MainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //Metodo Realizar Llamada
    public void callNumber() {
        String number = phone.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(SiguienteActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SiguienteActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(SiguienteActivity.this, "Ingresa Numero Telefonico", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callNumber();
            } else {
                Toast.makeText(this, "Permiso Denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Abre la aplicacion de contactos
    public void openContacts() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
        startActivity(intent);
    }
}
