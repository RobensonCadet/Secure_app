package com.example.secure_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.MessageDigestSpi;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.SecretKeySpec;

public class Generate_act extends AppCompatActivity {

    private EditText edit;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_act);

        edit = (EditText) findViewById(R.id.et);
        text = (TextView) findViewById(R.id.tv);
    }


    //Método para generar una key o una llave.
    private SecretKeySpec generateKey(String password) throws Exception{
            MessageDigest sha = MessageDigest.getInstance("SHA-256");  //Firma hmac para verificar la integridad de los datos.
            byte[] key = password.getBytes("UTF-8"); // El estandar del password va a ser bajo UTF
            key = sha.digest(key);   //Pasamos la firma hmac a nuestra cadena de byte.
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");

            return secretKey;
    }

    //Método para encriptar bajo el algoritmo AES.
    public String Encriptar(String datos, String password) throws Exception {
        if (!edit.getText().toString().isEmpty()) {

            //Hago el encriptado de datos.
            SecretKeySpec secretKey = generateKey(password);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);  //Hago el encriptado bajo CIPHER.

            byte[] datosEncriptadosBytes = cipher.doFinal(datos.getBytes());
            String datosEncriptadosString = Base64.encodeToString(datosEncriptadosBytes, Base64.DEFAULT);
            return datosEncriptadosString;
        } else {
            //Notificación en el dispositivo.
            Toast.makeText(this, "Debe ingresar una clave", Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void Encriptar(View view)
        {
            try {
                String mensaje = Encriptar(edit.getText().toString(), text.getText().toString());
                text.setText(mensaje);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }


}