package org.incoder.fragmenttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.incoder.fragmenttest.news.NewsActivity;
import org.incoder.fragmenttest.official.ItemListActivity;

/**
 * MainActivity
 *
 * @author Jerry xu
 * @date 4/6/2018 9:00 AM.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mFragment;
    private Button mFlowActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragment = findViewById(R.id.btn_fragment);
        mFlowActivity = findViewById(R.id.btn_flow);

        mFragment.setOnClickListener(this);
        mFlowActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 打开自定义学习Fragment
            case R.id.btn_fragment:
                Intent newsIntent = new Intent(this, NewsActivity.class);
                startActivity(newsIntent);
                break;
            // 打开使用Master/Detail Flow 模板
            case R.id.btn_flow:
                Intent intent = new Intent(this, ItemListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
