package org.incoder.activitytest;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * ShowActivity 被显示启动的Activity
 *
 * @author Jerry xu
 * @date 4/5/2018 5:00 PM.
 */
public class ShowActivity extends AppCompatActivity {

    private TextView showText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        showText = findViewById(R.id.tv_show);
        showText.setText("被显示Intent启动");
    }
}
