package com.alizhezi.demo.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.alizhezi.demo.R;

public class SendReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    private NetworkStateReceiver mNetworkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_receiver);

        mNetworkStateReceiver = new NetworkStateReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        registerReceiver(mNetworkStateReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(mNetworkStateReceiver);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.send_broadcast:

                Intent intent = new Intent("com.alizhezi.data.TEST_BROADCAST");

                intent.setPackage("com.alizhe.broadcastdemo");

                sendBroadcast(intent);

                break;
        }
    }
}
