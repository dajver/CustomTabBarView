package com.project.customviewproject.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.customviewproject.R;

import butterknife.ButterKnife;

/**
 * Created by gleb on 5/24/17.
 */

public class LastMonthFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_last_month, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
