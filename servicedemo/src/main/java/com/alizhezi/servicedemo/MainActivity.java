package com.alizhezi.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, ServiceDemo.class);

        intent.putExtra("data", "来自Activity");
    }


    public void startService(View view) {

        startService(intent);
    }

    public void stopService(View view) {
        stopService(intent);

    }
}
