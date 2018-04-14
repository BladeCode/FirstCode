package org.incoder.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * MainActivity
 *
 * @author Jerry xu
 * @date 4/5/2018 3:00 PM.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button mImplicit;
    private Button mShow;
    private Button mSendData;
    private Button mLifeCycle;
    private Button mStandard;
    private Button mSingleTop;
    private Button mSingleTask;
    private Button mSingleInstance;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*// 隐藏标题栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }*/
        setContentView(R.layout.activity_main);
        mShow = findViewById(R.id.btn_start_show);
        mImplicit = findViewById(R.id.btn_start_implicit);
        mSendData = findViewById(R.id.btn_send_data);
        mLifeCycle = findViewById(R.id.btn_life_cycle);
        mStandard = findViewById(R.id.btn_standard);
        mSingleTop = findViewById(R.id.btn_singletop);
        mSingleTask = findViewById(R.id.btn_singletask);
        mSingleInstance = findViewById(R.id.btn_singleinstance);

        mShow.setOnClickListener(this);
        mImplicit.setOnClickListener(this);
        mImplicit.setOnLongClickListener(this);
        mSendData.setOnClickListener(this);
        mSendData.setOnLongClickListener(this);
        mLifeCycle.setOnClickListener(this);
        mStandard.setOnClickListener(this);
        mSingleTop.setOnClickListener(this);
        mSingleTask.setOnClickListener(this);
        mSingleInstance.setOnClickListener(this);

        // 如果被系统销毁当前Activity,回退到当前Activity，会重新执行onCreate，而不是直接执行onRestart方法
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("key");
            Log.d(TAG, tempData);
        }
        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 显示启动Activity
            case R.id.btn_start_show:
                Intent showIntent = new Intent(this, ShowActivity.class);
                startActivity(showIntent);
                break;
            // 隐式启动Activity
            case R.id.btn_start_implicit:
                Intent implicitIntent = new Intent("org.incoder.activitytest.ACTION_START");
                startActivity(implicitIntent);
                break;
            // 传递数据
            case R.id.btn_send_data:
                Intent sendDataIntent = new Intent(this, DataTransferActivity.class);
                sendDataIntent.putExtra("sendData", "MainActivity send to DataTransferActivity");
                startActivity(sendDataIntent);
                break;
            // Activity生命周期
            case R.id.btn_life_cycle:
                Intent lifeCycleIntent = new Intent(this, LifeCycleActivity.class);
                startActivity(lifeCycleIntent);
                break;
            // standard启动
            case R.id.btn_standard:
                Intent standardIntent = new Intent(this, MainActivity.class);
                startActivity(standardIntent);
                break;
            // singleTop启动
            case R.id.btn_singletop:
                Intent singleTopIntent = new Intent(this, SingleTopActivity.class);
                startActivity(singleTopIntent);
                break;
            // singleTask启动
            case R.id.btn_singletask:
                Intent singleTaskIntent = new Intent(this, SingleTopActivity.class);
                startActivity(singleTaskIntent);
                break;
            // singleInstance启动
            case R.id.btn_singleinstance:
                Intent singleInstanceIntent = new Intent(this, SingleTopActivity.class);
                startActivity(singleInstanceIntent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            // 长按隐式启动
            case R.id.btn_start_implicit:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "测试");
                sendIntent.setType("text/plain");
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendIntent);
                }
                break;
            // 长按传递数据并回调
            case R.id.btn_send_data:
                Intent sendDataIntent = new Intent(this, DataTransferActivity.class);
                sendDataIntent.putExtra("sendData", "MainActivity send to DataTransferActivity");
                startActivityForResult(sendDataIntent, 0);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "OnClick Settings", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            String textContent = data.getStringExtra("returnData");
            Snackbar.make(mSendData, textContent, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    /**
     * 当前Activity被系统销毁前调用，可以用来保存临时数据
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", "value");
    }
}
