package com.example.gomraft.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gomraft.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BottomSheetDetailScheduleFragment extends BottomSheetDialogFragment {

    public BottomSheetDetailScheduleFragment() {
        // Required empty public constructor
    }

    public static BottomSheetDetailScheduleFragment newInstance(String param1, String param2) {
        BottomSheetDetailScheduleFragment fragment = new BottomSheetDetailScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.detail_schedule_bottom_sheet, container, false);
    }
}