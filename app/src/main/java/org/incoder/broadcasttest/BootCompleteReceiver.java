package org.incoder.broadcasttest;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * BroadcastTest
 *
 * @author Jerry xu
 * @date 4/7/2018 1:46 AM.
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "监听到开机启动广播", Toast.LENGTH_SHORT).show();
    }
}
