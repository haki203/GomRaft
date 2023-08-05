package com.example.gomraft.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.gomraft.Fragment.ScheduleExamFragment;
import com.example.gomraft.Fragment.ScheduleStudyFragment;

import java.util.List;
// adapter view pager lịch hoc -thi
public class ScheduleAdapter extends FragmentStateAdapter {

    String filteredList;
    public ScheduleAdapter(@NonNull Fragment fragment, String filteredList) {
        super(fragment);
        this.filteredList = filteredList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0 ) return  ScheduleStudyFragment.newInstance(filteredList);
        else return ScheduleExamFragment.newInstance(filteredList);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
