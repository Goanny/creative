package com.example.codigobarra;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    TextView mesa;
    Button aceptar;
    String datos ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mesa = findViewById(R.id.mensaje);
        aceptar= findViewById(R.id.boton);



    IntentIntegrator integrator=new IntentIntegrator(this);

    aceptar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            integrator.initiateScan();

        }
    });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        datos= result.getContents();
        mesa.setText(datos);
        //mesa.getText().toString();

       //String numCadena= Integer.toString(Integer.parseInt(datos));

        if(ActivityCompat.checkSelfPermission(
                MainActivity.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(
                MainActivity.this,Manifest
                        .permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]
                    { Manifest.permission.SEND_SMS,},1000);
        }else{
        };
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(mesa.getText().toString(),null,"Colegio Fe y Alegria, Asistencia de alumno",null,null);
        Toast.makeText(getApplicationContext(), "Mensaje Enviado.", Toast.LENGTH_LONG).show();


    }


}











