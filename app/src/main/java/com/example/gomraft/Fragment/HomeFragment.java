package com.example.gomraft.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gomraft.Adapter.ThongTinAdapter;
import com.example.gomraft.HomeActivity;
import com.example.gomraft.Model.ThongTin;
import com.example.gomraft.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private View view;
    private RecyclerView rcv;
    private ThongTinAdapter thongTinAdapter;
    private HomeActivity homeActivity;
    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        rcv = view.findViewById(R.id.rcv);
        homeActivity = (HomeActivity) getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homeActivity);
        rcv.setLayoutManager(linearLayoutManager);
        thongTinAdapter = new ThongTinAdapter(getListThongTin());
        rcv.setAdapter(thongTinAdapter);

        return view;
    }

    private List<ThongTin> getListThongTin() {
        List<ThongTin> list =new ArrayList<>();

        list.add(new ThongTin(R.drawable.ong1, "Người đăng: ThoRac", "Thông báo trốn nợ đi bụi không chịu về", "Thời gian: 23/7/2023"));
        list.add(new ThongTin(R.drawable.ong1, "Người đăng: BaoDog", "Sau khi ra trường không có việc làm nên đi Nghĩa vụ quân sự", "Thời gian: 23/7/2023 "));
        list.add(new ThongTin(R.drawable.ong1, "Người đăng: BaoDog", "Sau khi ra trường không có việc làm nên đi Nghĩa vụ quân sự", "Thời gian: 23/7/2023 "));
        list.add(new ThongTin(R.drawable.ong1, "Người đăng: BaoDog", "Sau khi ra trường không có việc làm nên đi Nghĩa vụ quân sự", "Thời gian: 23/7/2023 "));
        list.add(new ThongTin(R.drawable.ong1, "Người đăng: BaoDog", "Sau khi ra trường không có việc làm nên đi Nghĩa vụ quân sự", "Thời gian: 23/7/2023 "));


        return list;
    }
}