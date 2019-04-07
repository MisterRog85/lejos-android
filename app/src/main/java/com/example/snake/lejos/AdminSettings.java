package com.example.snake.lejos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AdminSettings extends AppCompatActivity {

    public EditText text;
    public BluetoothConnector bluetoothConnector;

    public SharedPreferences mPreferences;
    public SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        bluetoothConnector = new BluetoothConnector(this);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        updateView();

    }

    public void update(View view) {
        mEditor.putString("mac", text.getText().toString());
        mEditor.commit();
        bluetoothConnector.setEv3MAC(mPreferences.getString("mac", ""));
        updateView();
    }

    public void updateView(){
        text = (EditText) findViewById(R.id.mac);

        text.setText(bluetoothConnector.getEv3MAC());
    }

    public void retour(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
