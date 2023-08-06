package com.example.gomraft.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.gomraft.Adapter.ScheduleAdapter;
import com.example.gomraft.R;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class NotesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NotesFragment() {
        // Required empty public constructor
    }

    public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private ChipGroup chipGroup;
    String filteredList = "7 ngày tới";
    private ViewPager2 viewPager2;
    private Button btnStudy, btnExam;
    private MaterialButtonToggleGroup materialButtonToggleGroup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.scheduleViewPager);
        btnExam = view.findViewById(R.id.btnScheduleExam);
        materialButtonToggleGroup = view.findViewById(R.id.toggleButtonLayout);
        btnStudy = view.findViewById(R.id.btnScheduleStudy);
        chipGroup = view.findViewById(R.id.chipGroup);
        btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(0, true);
            }
        });

        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(1, true);
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == 0) {
                    materialButtonToggleGroup.check(R.id.btnScheduleStudy);
                }else if (position == 1){
                    materialButtonToggleGroup.check(R.id.btnScheduleExam);
                }
            }
        });
        chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                Chip chip = chipGroup.findViewById(chipGroup.getCheckedChipId());
                if (chip != null) {
                    String label = chip.getText().toString();
                    filteredList = label;
                    ScheduleAdapter scheduleAdapter = new ScheduleAdapter(NotesFragment.this,label);
                    viewPager2.setAdapter(scheduleAdapter);
                }
            }
        });

        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, filteredList);
        viewPager2.setAdapter(scheduleAdapter);
    }
}