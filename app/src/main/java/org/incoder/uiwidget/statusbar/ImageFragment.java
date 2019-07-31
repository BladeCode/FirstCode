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

package org.incoder.uiwidget.statusbar;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import org.incoder.uiwidget.R;

/**
 * StatusBarActivity
 *
 * @author Jerry xu
 * @date 7/29/2019 1:00 AM.
 */
public class ImageFragment extends Fragment {


    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0
            Window window = getActivity().getWindow();
            // 确认取消半透明设置
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 全屏显示，status bar 不隐藏，activity 上方layout会被status bar覆盖
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // 配合其他flag使用，防止system bar改变layout的变动
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            // 跟系统标识要渲染system bar背景
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false);
    }

}
