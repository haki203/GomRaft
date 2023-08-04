package com.example.gomraft.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.gomraft.Fragment.ScheduleExamFragment;
import com.example.gomraft.Fragment.ScheduleStudyFragment;

public class ScheduleAdapter extends FragmentStateAdapter {


    public ScheduleAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0 ) return  new ScheduleStudyFragment();
        else return new ScheduleExamFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
