package com.example.gomraft.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gomraft.Dto.ListScheduleSubjectResponseDTO;
import com.example.gomraft.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class BottomSheetDetailScheduleFragment extends BottomSheetDialogFragment {
    private ListScheduleSubjectResponseDTO.SubjectResponseDTO subject;
    private TextView txtDay, txtName, txtAddress, txtRoom, txtClass, txtTime, txtTeacher;
    public BottomSheetDetailScheduleFragment(ListScheduleSubjectResponseDTO.SubjectResponseDTO subject) {
        // Required empty public constructor
        this.subject = subject;
    }

//    public static BottomSheetDetailScheduleFragment newInstance(String param1, String param2) {
//        BottomSheetDetailScheduleFragment fragment = new BottomSheetDetailScheduleFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.detail_schedule_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtDay = view.findViewById(R.id.txtDate);
        txtName = view.findViewById(R.id.txtName);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtRoom = view.findViewById(R.id.txtRoom);
        txtClass = view.findViewById(R.id.txtClass);
        txtTeacher = view.findViewById(R.id.txtMentor);
        txtTime = view.findViewById(R.id.txtDuration);

        if(subject == null) return;
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String outputDate = subject.getDay();
        try {
            Date date = inputDateFormat.parse(subject.getDay());
            if (date != null) {
                outputDate = outputDateFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtDay.setText(outputDate);
        txtName.setText(subject.getCourse_name());
        txtAddress.setText(subject.getAddress());
        txtRoom.setText(subject.getRoom());
        txtClass.setText(subject.getClass_name());
        txtTeacher.setText(subject.getTeacher_name());
        txtTime.setText(convertShiftToTime(subject.getTime()));

    }

//    chuyển ca sang giờ
    private String convertShiftToTime(String time) {
        switch (time) {
            case "Ca 1":
                return "07:30 - 9:30";
            case "Ca 2":
                return "09:45 - 11:45";
            case "Ca 3":
                return "13:00 - 15:00";
            case "Ca 4":
                return "15:15 - 17:15";
            case "Ca 5":
                return "17:30 - 19:30";
            case "Ca 6":
                return "19:45 - 21:45";
            default:
                return time;
        }
    }
}