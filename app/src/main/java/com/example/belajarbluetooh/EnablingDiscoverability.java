package com.example.belajarbluetooh;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EnablingDiscoverability extends AppCompatActivity {

    Button buttonEnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enabling_discoverability);

        buttonEnable = findViewById(R.id.enablingDiscoverability);

        buttonEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                //untuk memunculkan bluetooth dalam durasi tertentu
                //kodingan di bawah ini bluetooth hanya menampilkan dalam 10 detik
                i.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,10);
                startActivity(i);
            }
        });

    }
}
