package com.example.snake.lejos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    //Context ctx;
    String password = "admin";

    public EditText mdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mdp = (EditText) findViewById(R.id.password);
    }

    public void connexionAdmin (View view) {
        Log.d("log1 le mdp lu", mdp.getText().toString());
        if (mdp.getText().toString().equals(password)){
            Intent intent = new Intent(this, AdminSettings.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Erreur mot de passe", Toast.LENGTH_LONG).show();
        }
    }
}
