package com.example.snake.lejos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public Button connexion;
    public TextView etat;


    public BluetoothConnector bluetoothConnector;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connexion = (Button) findViewById(R.id.connexion);
        etat = (TextView) findViewById(R.id.etat);

        bluetoothConnector = new BluetoothConnector(this);

    }

    public void connexionAdmin(View view){

        Intent intent = new Intent(this, AdminLogin.class);

        startActivity(intent);
    }

    public void connexion(View view) {
        if(bluetoothConnector.getIsConnected()){
            bluetoothConnector.disconnectEV3(view, this);
            connexion.setText("Se connecter");
        } else {

            if(bluetoothConnector.connectEV3(view, this)){
                connexion.setText("Se déconnecter");
            };
        }
    }

    public void ouvrir (View view) {
        bluetoothConnector.sendMessage(1);
        etat.setText("En ouverture");
    }

    public void fermer (View view) {
        bluetoothConnector.sendMessage(2);
        etat.setText("En fermeture");
    }
}

