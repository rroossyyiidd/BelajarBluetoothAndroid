package com.example.belajarbluetooh;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int REQUEST_ENABLE_BT = 1; //declare globally
    Button buttonOn, buttonOff, buttonPairedDevices, buttonScan, buttonEnabling, buttonIntroHander;
    BluetoothAdapter bluetoothAdapter;
    Intent enableBtIntent, i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOn = findViewById(R.id.btOn);
        buttonOff = findViewById(R.id.btOff);
        buttonPairedDevices = findViewById(R.id.btNext);
        buttonScan = findViewById(R.id.btScan);
        buttonEnabling= findViewById(R.id.btEnabling);
        buttonIntroHander = findViewById(R.id.btIntroHandler);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        setButtonOn();
        setButtonOff();

        //ke halaman paired device
        buttonPairedDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), PairedDevices.class);
                startActivity(i);
            }
        });

        //ke halaman scan device
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), ScanNewDevices.class);
                startActivity(i);
            }
        });

        //ke halaman enabling discoverability
        buttonEnabling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), EnablingDiscoverability.class);
                startActivity(i);
            }
        });

        //ke halaman intro handler
        buttonIntroHander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), IntroHandler.class);
                startActivity(i);
            }
        });
    }

    public void setButtonOff() {
        buttonOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.disable();
                }
            }
        });
    }

    public void setButtonOn() {
        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter == null) {
                    //device dont support bluetooth
                    Toast.makeText(getApplicationContext(), "Device tidak support bluetooth", Toast.LENGTH_SHORT).show();
                } else {
                    //check bluetooth kalau off
                    if (!bluetoothAdapter.isEnabled()) {
                        enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_OK) {
                //bt enable
                Toast.makeText(getApplicationContext(), "Bluetooth menyala", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                //bt enabling canceled
                Toast.makeText(getApplicationContext(), "Bluetooth tidak menyala", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
