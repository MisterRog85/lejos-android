package com.example.snake.lejos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    Context ctx;
    String password = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        final EditText mdp = (EditText) findViewById(R.id.password);
        final Button valider = (Button) findViewById(R.id.valider);


        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mdp.toString() == password){
                    Intent intent = new Intent(ctx, AdminSettings.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ctx, "Erreur mot de passe", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
