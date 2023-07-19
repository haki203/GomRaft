package com.example.gomraft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;

import com.example.gomraft.Adapter.AdapterViewPager;
import com.example.gomraft.Fragment.ComentsFragment;
import com.example.gomraft.Fragment.HomeFragment;
import com.example.gomraft.Fragment.NotesFragment;
import com.example.gomraft.Fragment.ProifileFragment;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ViewPager2 pager;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_xml);
        pager = findViewById(R.id.pearmain);

        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new NotesFragment());
        fragmentArrayList.add(new ComentsFragment());
        fragmentArrayList.add(new ProifileFragment());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this, fragmentArrayList);
        pager.setAdapter(adapterViewPager);

    }
}