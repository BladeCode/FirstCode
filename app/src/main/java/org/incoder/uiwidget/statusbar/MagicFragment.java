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


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import org.incoder.uiwidget.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 魔法状态栏
 *
 * @author Jerry xu
 * @date 7/29/2019 1:00 AM.
 */
public class MagicFragment extends Fragment {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_transparency)
    TextView tvTransparency;
    @BindView(R.id.sb_transparency)
    SeekBar sbTransparency;

    Unbinder unbinder;

    public MagicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_magic, container, false);
        unbinder = ButterKnife.bind(this, view);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.info, menu);
//        super.onCreateOptionsMenu(menu,inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//
//    }
}
