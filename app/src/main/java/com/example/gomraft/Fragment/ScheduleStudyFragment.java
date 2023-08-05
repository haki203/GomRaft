package com.example.gomraft.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gomraft.Adapter.ScheduleStudyAdapter;
import com.example.gomraft.Dto.ListScheduleSubjectResponseDTO;
import com.example.gomraft.Helpers.IRetrofit;
import com.example.gomraft.Helpers.RetrofitHelper;
import com.example.gomraft.R;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleStudyFragment extends Fragment {
    private static final String ARG_DATA = "data";
    int DATE = 0;
    int ASC = 1;
    int USER_ID = 1;
    private RecyclerView rcvSchedule;
    private CircularProgressIndicator mProgressIndicator;
    private IRetrofit iRetrofit;
    ScheduleStudyAdapter scheduleStudyAdapter;
    private List<ListScheduleSubjectResponseDTO.SubjectResponseDTO> subjectResponseDTOList;

    public static ScheduleStudyFragment newInstance(String data) {
        ScheduleStudyFragment fragment = new ScheduleStudyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            String data = args.getString(ARG_DATA);
            switch (data) {
                case "7 ngày tới":
                    DATE = 7;
                    ASC = 1;
                    break;
                case "14 ngày tới":
                    DATE = 14;
                    ASC = 1;
                    break;
                case "30 ngày tới":
                    DATE = 30;
                    ASC = 1;
                    break;
                case "7 ngày trước":
                    DATE = 7;
                    ASC = -1;
                    break;
                case "14 ngày trước":
                    DATE = 14;
                    ASC = -1;
                    break;
                case "30 ngày trước":
                    DATE = 30;
                    ASC = -1;
                    break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule_study, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvSchedule = view.findViewById(R.id.rcvScheduleStudy);
        mProgressIndicator = view.findViewById(R.id.progress_circular);
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
        //gan adapter vo recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        rcvSchedule.setLayoutManager(linearLayoutManager);
        scheduleStudyAdapter = new ScheduleStudyAdapter();
        rcvSchedule.setAdapter(scheduleStudyAdapter);
        mProgressIndicator.setVisibility(View.VISIBLE);
    }

    Callback<ListScheduleSubjectResponseDTO> responseDTOCallback = new Callback<ListScheduleSubjectResponseDTO>() {
        @Override
        public void onResponse(Call<ListScheduleSubjectResponseDTO> call, Response<ListScheduleSubjectResponseDTO> response) {
            if (response.isSuccessful()) {
                ListScheduleSubjectResponseDTO listScheduleSubjectResponseDTO = response.body();
                if (listScheduleSubjectResponseDTO != null) {
                    if (listScheduleSubjectResponseDTO.isStatus()) {
                        subjectResponseDTOList = listScheduleSubjectResponseDTO.getSubjectResponseDTOList();
                        scheduleStudyAdapter.setData(subjectResponseDTOList);
                        mProgressIndicator.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(getContext(),
                                "onResponse Không thành công",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(),
                            "onResponse Không thành công",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<ListScheduleSubjectResponseDTO> call, Throwable t) {
            Log.e(ScheduleExamFragment.class.getSimpleName(), "onFailure: " + t.getMessage());
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        iRetrofit.getSchedule(DATE, ASC, 0, USER_ID).enqueue(responseDTOCallback);
    }

}