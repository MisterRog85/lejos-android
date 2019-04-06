package com.example.snake.lejos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.w3c.dom.Text;

public class AdminSettings extends AppCompatActivity {

    public EditText text;
    public BluetoothConnector bluetoothConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        bluetoothConnector = new BluetoothConnector(this);

        text = (EditText) findViewById(R.id.mac);

        text.setText(bluetoothConnector.getNxtMAC());
    }
}
