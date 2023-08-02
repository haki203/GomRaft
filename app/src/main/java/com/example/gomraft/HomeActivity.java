package com.example.gomraft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;
import android.view.MenuItem;

import com.example.gomraft.Adapter.AdapterViewPager;
import com.example.gomraft.Fragment.ComentsFragment;
import com.example.gomraft.Fragment.HomeFragment;
import com.example.gomraft.Fragment.NotesFragment;
import com.example.gomraft.Fragment.ProifileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ViewPager2 pager;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_xml);
        pager = findViewById(R.id.pearmain);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new NotesFragment());
        fragmentArrayList.add(new ComentsFragment());
        fragmentArrayList.add(new ProifileFragment());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this, fragmentArrayList);
        pager.setAdapter(adapterViewPager);
        pager.setUserInputEnabled(false);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.note);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.coment);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.user);
                        break;
                }
                super.onPageSelected(position);
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.note:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.coment:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.user:
                        pager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }
}