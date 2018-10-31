package com.alizhezi.demo.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alizhezi.demo.IAdditionService;
import com.alizhezi.demo.MainActivity;
import com.alizhezi.demo.R;
import com.alizhezi.demo.base.BaseActivity;

public class MainStateActivity extends BaseActivity implements View.OnClickListener {

    private static final int PUSH_NOTIFICATION_ID = (0x001);
    private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
    private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";
    private NotificationManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_state);

        if (savedInstanceState != null) {

            Log.e(TAG, "onCreate:" + savedInstanceState.getString("temp"));
        }

        mManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);//
        // 获取状态栏通知的管理类（负责发通知、清除通知等操作）

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.e(TAG, "onRestoreInstanceState:" + savedInstanceState.getString("temp"));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("temp", "保存的数据");

        Log.e(TAG, "onSaveInstanceState:");
    }

    private IAdditionService mIAdditionService;
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mIAdditionService = IAdditionService.Stub.asInterface(service);

            try {
                int add = mIAdditionService.add(4, 5);

                Log.e(TAG, "返回的结果：" + add);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_show:

                Toast.makeText(MainStateActivity.this, "show", Toast.LENGTH_SHORT).show();
                showNotification();

                break;

            case R.id.btn_hide:
                Toast.makeText(MainStateActivity.this, "hide", Toast.LENGTH_SHORT).show();

                mManager.cancel(1);

                break;

            case R.id.bind_service:

                Intent intent = new Intent("com.alizhezi.demo.AidlService");

                intent.setPackage("com.alizhezi.demo");

                bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

                break;

            case R.id.unbind_service:

                unbindService(mConnection);

                break;
        }
    }

    private void showNotification() {

        Intent intent = new Intent(this, MainActivity.class);

        Notification notification = null;

        PendingIntent pendingIntent =
            PendingIntent.getActivity(MainStateActivity.this, 0, intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                new NotificationChannel("1", "mi", NotificationManager.IMPORTANCE_LOW);

            mManager.createNotificationChannel(channel);

            //Notification.Builder builder=new Notification.Builder(this);
            //builder.setChannelId("1");
            //builder.setContentTitle("前台服务通知的标题");
            //
            //builder.setContentText("前台服务通知的内容");
            //builder.setSmallIcon(R.mipmap.ic_launcher);
            //
            //builder.setContentIntent(pendingIntent);
            //builder.setWhen(System.currentTimeMillis());       //设置通知时间

            notification = new Notification.Builder(this).setChannelId("1")
                                                         .setContentTitle("前台服务通知的标题")
                                                         .setContentText("前台服务通知的内容")
                                                         .setSmallIcon(R.mipmap.ic_launcher)
                                                         .build();
        } else {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("前台服务通知的标题");

            builder.setContentText("前台服务通知的内容");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentIntent(pendingIntent);
            builder.setWhen(System.currentTimeMillis());       //设置通知时间

            notification = builder.build();
        }

        mManager.notify(1000, notification);
    }
}
