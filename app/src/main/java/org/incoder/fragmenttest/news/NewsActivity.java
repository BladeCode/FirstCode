package org.incoder.fragmenttest.news;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.incoder.fragmenttest.R;

/**
 * NewsActivity
 *
 * @author Jerry xu
 * @date 4/6/2018 9:00 AM.
 */
public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        NewsItemFragment fragment = new NewsItemFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_list, fragment);
        transaction.commit();


    }
}
