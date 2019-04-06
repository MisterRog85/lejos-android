package com.example.snake.lejos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    public Button connexion;
    public TextView etat;

    public BluetoothConnector bluetoothConnector = new BluetoothConnector(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connexion = (Button) findViewById(R.id.connexion);
        etat = (TextView) findViewById(R.id.etat);
    }

    public void connexionAdmin(View view){

        Intent intent = new Intent(this, AdminLogin.class);

        startActivity(intent);
    }

    public void connexion(View view) {
        if(bluetoothConnector.getIsConnected()){
            bluetoothConnector.disconnectNXT(view, this);
            connexion.setText("Se connecter");
        } else {

            if(bluetoothConnector.connectNXT(view, this)){
                connexion.setText("Se déconnecter");
            };
        }
    }

    public void ouvrir (View view) {
        bluetoothConnector.sendMessage(1);
        etat.setText("En ouverture");
    }

    public void fermer (View view) {
        bluetoothConnector.sendMessage(1);
        etat.setText("En fermeture");
    }
}

