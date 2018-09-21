package com.example.belajarbluetooh;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Set;

public class PairedDevices extends AppCompatActivity {

    Button btScan;
    ListView listView;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bluetooth_device);
        btScan = findViewById(R.id.btShowPairedDevices);
        listView = findViewById(R.id.listDevice);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        setBtScan();
    }

    //eksekusi scan button
    public void setBtScan() {
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<BluetoothDevice> bluetoothDevices = bluetoothAdapter.getBondedDevices();
                String[] strings = new String[bluetoothDevices.size()];
                int index = 0;

                if (bluetoothDevices.size()>0){
                    for (BluetoothDevice device:bluetoothDevices){
                        strings[index] = device.getName();
                        index++;
                    }
                    //memasukkan ke dalam daftar device on bluetooth
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, strings);
                    listView.setAdapter(arrayAdapter);
                }
            }
        });
    }
}
