package org.incoder.activitytest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * LifeCycleActivity Activity生命周期
 *
 * @author Jerry xu
 * @date 4/5/2018 7:00 PM.
 */
public class LifeCycleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LifeCycleActivity";
    private Button mDialog;

    /**
     * Activity第一次被创建时调用
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        mDialog = findViewById(R.id.btn_dialog);
        mDialog.setOnClickListener(this);
        Log.i(TAG, "onCreate");
    }

    /**
     * Activity由“停止”状态变为“继续”状态之前调用
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * Activity由不可见变为可见时调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    /**
     * Activity准备好和用户进行交互时调用（此时Activity一定位于栈顶，并且处于“继续”状态）
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    /**
     * 系统准备启动或者恢复另一个Activity时调用
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onStart");
    }

    /**
     * Activity完全不可见时调用
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    /**
     * Activity被销毁之前调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(LifeCycleActivity.this);
                builder.setTitle("这是对话框Dialog")
                        .setMessage("这里是主体内容。。。")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(LifeCycleActivity.this, "close dialog", Toast.LENGTH_SHORT).show();
                            }
                        }).create().show();
                break;
            default:
                break;
        }
    }
}
