package com.example.gomraft.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gomraft.Adapter.ScheduleStudyAdapter;
import com.example.gomraft.R;
import com.example.gomraft.models.Subject;

import java.util.ArrayList;
import java.util.List;

public class ScheduleExamFragment extends Fragment {

    private RecyclerView rcvSchedule;
    public ScheduleExamFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_exam, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvSchedule = view.findViewById(R.id.rcvScheduleExam);

        //gan adapter vo recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        rcvSchedule.setLayoutManager(linearLayoutManager);

        ScheduleStudyAdapter scheduleStudyAdapter = new ScheduleStudyAdapter();
        scheduleStudyAdapter.setData(getListScheduleSubject());
        rcvSchedule.setAdapter(scheduleStudyAdapter);
    }

    private List<Subject> getListScheduleSubject() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("1",
                "Bao ve Lap trinh mobile 1",
                "17:00 : 19:00",
                "T308",
                "chann3",
                "teacher image",
                "22/05",
                "Ca 5"));
        subjects.add(new Subject("2",
                "Lap trinh mobile 2",
                "17:00 : 19:00",
                "T308",
                "chann3",
                "teacher image",
                "22/05",
                "Ca 5"));
        subjects.add(new Subject("3",
                "Lap trinh mobile 3",
                "17:00 : 19:00",
                "T308",
                "chann3",
                "teacher image",
                "22/05",
                "Ca 5"));
        subjects.add(new Subject("4",
                "Lap trinh mobile 4",
                "17:00 : 19:00",
                "T308",
                "chann3",
                "teacher image",
                "22/05",
                "Ca 5"));
        subjects.add(new Subject("5",
                "Lap trinh mobile 5",
                "17:00 : 19:00",
                "T308",
                "chann3",
                "teacher image",
                "22/05",
                "Ca 5"));
        subjects.add(new Subject("6",
                "Lap trinh mobile 6",
                "17:00 : 19:00",
                "T308",
                "chann3",
                "teacher image",
                "22/05",
                "Ca 5"));
        subjects.add(new Subject("7",
                "Lap trinh mobile 7",
                "17:00 : 19:00",
                "T308",
                "chann3",
                "teacher image",
                "22/05",
                "Ca 5"));
        subjects.add(new Subject("7",
                "Lap trinh mobile 7",
                "17:00 : 19:00",
                "T308",
                "chann3",
                "teacher image",
                "22/05",
                "Ca 5"));

        return subjects;
    }
}