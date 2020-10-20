package com.example.secure_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Vulnerabilidad_act extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerabilidad_act);

        text = (TextView)findViewById(R.id.tv);
    }


    public void Vulnerabilidad(View v)
    {
        class Echo{

            public native void runEcho();
            {
                System.loadLibrary("librería");  // Reemplazo de una librería maliciosa.
            }

            public void main(String[] args)
            {
                new Echo().runEcho();
            }

        }

        text.setText("Process Control: Fuentes no confiables.");
    }


    public void VulnerabilidadDos(View v)
    {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://webnosegura.cl"));
        startActivity(i);
        text.setText("Process Control: Fuentes no confiables.");

    }

    public void válidación()
    {
        String pass = "123";

        if(pass.equals(pass))
        {

        }
        text.setText("Hardcoded Password: Utilizar Contraseña Mas Fuerte. o algún cifrado.");

    }
}