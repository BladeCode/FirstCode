package org.incoder.broadcasttest;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * MainActivity
 *
 * @author Jerry xu
 * @date 4/7/2018 1:00 AM.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver receiver;
    private LocalBroadcastReceiver localBroadcastReceiver;
    private LocalBroadcastManager localBroadcastManager;

    private Button mSendStandardBroadcast;
    private Button mSendOrderlyBroadcast;
    private Button mSendLocalBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSendStandardBroadcast = findViewById(R.id.btn_send_standard);
        mSendOrderlyBroadcast = findViewById(R.id.btn_send_orderly);
        mSendLocalBroadcast = findViewById(R.id.btn_send_local);
        mSendStandardBroadcast.setOnClickListener(this);
        mSendOrderlyBroadcast.setOnClickListener(this);
        mSendLocalBroadcast.setOnClickListener(this);
        // 动态注册广播接收器
        intentFilter = new IntentFilter();
        // 监听网络变化广播通知
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        receiver = new NetworkChangeReceiver();
        registerReceiver(receiver, intentFilter);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter localIntent = new IntentFilter();
        localIntent.addAction("org.incoder.broadcasttest.LOCAL_BROADCAST");
        localBroadcastReceiver = new LocalBroadcastReceiver();
        localBroadcastManager.registerReceiver(localBroadcastReceiver, localIntent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 发送标准广播
            case R.id.btn_send_standard:
                // 自定义的action
                Intent intent = new Intent("org.incoder.broadcasttest.MY_STANDARD_BROADCAST");
                sendBroadcast(intent);
                break;
            // 发送有序广播
            case R.id.btn_send_orderly:
                Intent orderlyIntent = new Intent("org.incoder.broadcasttest.MY_ORDERLY-BROADCAST");
                // 第二参数是于权限相关的字符，这里传null即可
                sendOrderedBroadcast(orderlyIntent, null);
                // 发送的是有序广播，广播接收器是有先后顺序，前面的接收器可以截断广播，
                // 接收器优先级可以在AndroidManifest.xml文件中，receiver 中添加 android:priority="100"
                break;
            // 发送本地广播
            case R.id.btn_send_local:

                Intent localIntent = new Intent();
                localBroadcastManager.sendBroadcast(localIntent);
                break;
            default:
                break;
        }
    }


}
