package org.incoder.uiwidget.button;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.incoder.uiwidget.R;

/**
 * RadioGroupFragment
 *
 * @author Jerry xu
 * @date 4/6/2018 8:00 AM.
 */
public class RadioGroupFragment extends Fragment {


    public RadioGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio_group, container, false);
    }

}
