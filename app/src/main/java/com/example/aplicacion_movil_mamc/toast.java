package com.example.aplicacion_movil_mamc;

import android.content.Context;
import android.widget.Toast;

public class toast {
    public void mostrarToast(Context context){
        Toast.makeText(context,"Toast en la Clase",Toast.LENGTH_LONG).show();
    }
}
