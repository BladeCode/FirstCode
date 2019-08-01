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


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import org.incoder.uiwidget.R;

import java.util.Objects;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 渐变色
 *
 * @author Jerry xu
 * @date 7/29/2019 1:00 AM.
 */
public class GradientFragment extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rb_shape)
    RadioButton rbShape;
    @BindView(R.id.rb_img)
    RadioButton rbImg;
    @BindView(R.id.rb_light)
    RadioButton rbLight;
    @BindView(R.id.rb_black)
    RadioButton rbBlack;

    @BindDrawable(R.drawable.gradient_status_bar)
    Drawable shapeGradient;
    @BindDrawable(R.drawable.gradient_img)
    Drawable imgGradient;

    Unbinder unbinder;

    @OnClick({R.id.rb_shape, R.id.rb_img})
    void shapeGradient(View view) {
        if (view.getId() == R.id.rb_shape) {
            toolbar.setBackground(shapeGradient);
        } else {
            toolbar.setBackground(imgGradient);
        }
    }

    @OnClick({R.id.rb_light, R.id.rb_black})
    void selectMode(View view) {
        if (view.getId() == R.id.rb_light) {
            StatusUtils.setStatusBarLightMode(Objects.requireNonNull(getActivity()).getWindow(), false);
        } else {
            StatusUtils.setStatusBarLightMode(Objects.requireNonNull(getActivity()).getWindow(), true);
        }
    }

    public GradientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gradient, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
