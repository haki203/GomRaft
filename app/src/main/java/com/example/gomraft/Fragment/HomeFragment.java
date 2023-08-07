package com.example.gomraft.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.gomraft.Adapter.ThongTinAdapter;
import com.example.gomraft.HomeActivity;
import com.example.gomraft.Model.ThongTin;
import com.example.gomraft.R;
import com.example.gomraft.dto.ListPostsReponseDTO;
import com.example.gomraft.helpers.IRetrofit;
import com.example.gomraft.helpers.RetrofitHelpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private View view;
    private RecyclerView rcv;
    private ThongTinAdapter thongTinAdapter;
    private HomeActivity homeActivity;
    IRetrofit iRetrofit;
    List<ListPostsReponseDTO.PostsReponseDTO> list;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        rcv = view.findViewById(R.id.rcv);
        iRetrofit = RetrofitHelpers.createService(IRetrofit.class);
        homeActivity = (HomeActivity) getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(homeActivity);
        rcv.setLayoutManager(linearLayoutManager);
        thongTinAdapter = new ThongTinAdapter(list);
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

    @Override
    public void onResume() {
        super.onResume();
        iRetrofit.getAllProducts().enqueue(getListProductCallback);


    }
    Callback<ListPostsReponseDTO> getListProductCallback = new Callback<ListPostsReponseDTO>() {
        @Override
        public void onResponse(Call<ListPostsReponseDTO> call, Response<ListPostsReponseDTO> response) {
            if (response.isSuccessful()){
                ListPostsReponseDTO reponseDTO = response.body();
                list=reponseDTO.getPosts();
                thongTinAdapter = new ThongTinAdapter(list);
                rcv.setAdapter(thongTinAdapter);
            }
        }

        @Override
        public void onFailure(Call<ListPostsReponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };


}