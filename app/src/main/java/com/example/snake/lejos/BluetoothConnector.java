package com.example.snake.lejos;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class BluetoothConnector{

    private BluetoothAdapter localAdapter;
    private BluetoothSocket socket_nxt;
    private BluetoothDevice nxt;
    private OutputStreamWriter out;
    private String nxtMAC = "00:16:53:56:5F:C2"; //ici pour changer l'adresse MAc du robot
    private Boolean isConnected;

    public Context context;

    public String getNxtMAC() {
        return nxtMAC;
    }

    public void setNxtMAC(String nxtMAC) {
        this.nxtMAC = nxtMAC;
    }

    public BluetoothConnector(Context ctx) {
        this.context=ctx;
        this.localAdapter = BluetoothAdapter.getDefaultAdapter();
        this.setBluetoothON();
        this.isConnected = false;

    }

    public void setBluetoothON(){
        if (!this.localAdapter.isEnabled()) {
            this.localAdapter.enable();
            while(!(this.localAdapter.isEnabled()));
        }
    }
    public Boolean getIsConnected(){
        return this.isConnected;
    }

    public void setBluetoothOFF(){
        if (this.localAdapter.isEnabled()) {
            this.localAdapter.disable();
        }
    }

    public Boolean connectNXT(View v, Context ctx){
        this.context=ctx;
        this.nxt = this.localAdapter.getRemoteDevice(nxtMAC);
        try {
            this.socket_nxt = nxt.createRfcommSocketToServiceRecord(UUID
                    .fromString("00001101-0000-1000-8000-00805F9B34FB"));
            this.socket_nxt.connect();
            Toast.makeText(context, "Bluetooth: Connected to NXT", Toast.LENGTH_LONG).show();
            this.isConnected = true;
            return true;
        } catch (IOException e) {
            Toast.makeText(context, "Bluetooth: Device not found or cannot connect", Toast.LENGTH_LONG).show();
            return false;
        }
    }


    public void sendMessage(int msg){
        try {
            this.out = new OutputStreamWriter(socket_nxt.getOutputStream());
            this.out.write((byte)msg);
            this.out.flush();
            //  Thread.sleep(500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnectNXT(View v, Context ctx){
        this.context=ctx;
        try{
            this.out.close();
            this.socket_nxt.close();
            this.isConnected = false;
            Toast.makeText(context, "Bluetooth: Déconnecté", Toast.LENGTH_LONG).show();
        } catch (IOException e){
            Toast.makeText(context, "Bluetooth: Erreur déconnection", Toast.LENGTH_LONG).show();
        }

    }

}
