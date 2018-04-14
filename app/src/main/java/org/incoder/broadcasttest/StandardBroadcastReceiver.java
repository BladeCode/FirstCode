package org.incoder.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * BroadcastTest
 *
 * @author Jerry xu
 * @date 4/7/2018 2:05 AM.
 */
public class StandardBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received standard broadcast", Toast.LENGTH_SHORT).show();
    }
}
