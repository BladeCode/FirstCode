package org.incoder.activitytest;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * ImplicitActivity 被隐式启动的Activity
 *
 * @author Jerry xu
 * @date 4/5/2018 5:00 PM.
 */
public class ImplicitActivity extends AppCompatActivity {

    private TextView implicitText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);
        implicitText = findViewById(R.id.tv_implicit);

        implicitText.setText("被隐式Intent启动");
    }
}
