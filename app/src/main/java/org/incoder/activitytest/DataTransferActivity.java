package org.incoder.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * DataTransferActivity 数据传递
 *
 * @author Jerry xu
 * @date 4/5/2018 5:00 PM.
 */
public class DataTransferActivity extends AppCompatActivity {

    private TextView mReceiveText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_transfer);
        mReceiveText = findViewById(R.id.tv_data);
        mReceiveText.setText(getIntent().getStringExtra("sendData"));
    }

    @Override
    public void onBackPressed() {
        Intent returnDataIntent = new Intent();
        returnDataIntent.putExtra("returnData", "DataTransferActivity back to MainActivity");
        setResult(RESULT_OK, returnDataIntent);
        finish();
        super.onBackPressed();
    }
}
