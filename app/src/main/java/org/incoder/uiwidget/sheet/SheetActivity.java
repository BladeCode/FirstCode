/*
 * Copyright (C) 2018 The Jerry xu Open Source Project
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

package org.incoder.uiwidget.sheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.incoder.uiwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * SheetActivity
 *
 * @author Jerry xu
 * @date 8/1/2019 1:00 AM.
 */
public class SheetActivity extends AppCompatActivity implements BottomSheetFragment.Listener {

    @BindView(R.id.btn_open)
    Button mOpen;

    @OnClick(R.id.btn_open)
    void openSheets() {
        BottomSheetFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet);
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClicked(int position) {

    }
}
