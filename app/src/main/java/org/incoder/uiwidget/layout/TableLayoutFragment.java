package org.incoder.uiwidget.layout;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.incoder.uiwidget.R;

/**
 * TableLayoutFragment
 *
 * @author Jerry xu
 * @date 4/6/2018 8:00 AM.
 */
public class TableLayoutFragment extends Fragment {


    public TableLayoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_layout, container, false);
    }

}
