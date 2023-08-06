package com.example.gomraft.Fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gomraft.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProifileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProifileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String CHANNEL_ID = "CHANNEL_ID";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProifileFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ProifileFragment newInstance(String param1, String param2) {
        ProifileFragment fragment = new ProifileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        return inflater.inflate(R.layout.fragment_proifile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Thông báo";
            String description = "Thông báo";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("\uD83D\uDCE3\uD83D\uDCE3\uD83D\uDCE3 Đi học nào kẻo kẹt xe!!!");
        bigTextStyle.bigText("Môn học Android Networking sẽ bắt đầu vào CA 5. Bạn hãy nhớ nhé!");
        // Tạo thông báo bằng cách sử dụng NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.vector_thongbao) // Icon cho thông báo
                .setContentTitle("\uD83D\uDCE3\uD83D\uDCE3\uD83D\uDCE3 Đi học nào kẻo kẹt xe!!!") // Tiêu đề của thông báo
                .setContentText("Môn học Android Networking sẽ bắt đầu vào CA 5. Bạn hãy nhớ nhé!") // Nội dung của thông báo
                .setStyle(bigTextStyle)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        // Hiển thị thông báo
        NotificationManager notificationManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(/* ID thông báo */ 1, builder.build());
    }
}