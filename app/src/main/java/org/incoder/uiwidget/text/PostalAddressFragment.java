package org.incoder.uiwidget.text;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.incoder.uiwidget.R;

/**
 * PostalAddressFragment
 *
 * @author Jerry xu
 * @date 4/6/2018 8:00 AM.
 */
public class PostalAddressFragment extends Fragment {


    public PostalAddressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_postal_address, container, false);
    }

}
