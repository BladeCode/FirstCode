/*
 * Copyright (C) 2019 The Jerry xu Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.incoder.uiwidget.statusbar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.incoder.uiwidget.BasePagerAdapter;
import org.incoder.uiwidget.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * StatusBarActivity
 *
 * @author Jerry xu
 * @date 7/29/2019 1:00 AM.
 */
public class StatusBarActivity extends AppCompatActivity {

    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_image:
                    vpContent.setCurrentItem(0);
                    return true;
                case R.id.navigation_translucent:
                    vpContent.setCurrentItem(1);
                    return true;
                case R.id.navigation_gradient:
                    vpContent.setCurrentItem(2);
                    return true;
                case R.id.navigation_magic:
                    vpContent.setCurrentItem(3);
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar);
        ButterKnife.bind(this);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFragments.add(new ImageFragment());
        mFragments.add(new TranslucentFragment());
        mFragments.add(new GradientFragment());
        mFragments.add(new MagicFragment());
        vpContent.setOffscreenPageLimit(mFragments.size());
        vpContent.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), mFragments));
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    navView.setSelectedItemId(R.id.navigation_image);
                } else if (position == 1) {
                    navView.setSelectedItemId(R.id.navigation_translucent);
                } else if (position == 2) {
                    navView.setSelectedItemId(R.id.navigation_gradient);
                } else if (position == 3) {
                    navView.setSelectedItemId(R.id.navigation_magic);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
