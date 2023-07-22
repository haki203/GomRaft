package com.example.gomraft.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomraft.R;
import com.example.gomraft.models.Subject;

import java.util.List;

public class ScheduleStudyAdapter extends RecyclerView.Adapter<ScheduleStudyAdapter.ScheduleStudyViewHolder> {
    List<Subject> subjectList;
    private Context context;
    public void setData(List<Subject> subjectList){
        this.subjectList= subjectList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ScheduleStudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        return new ScheduleStudyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleStudyViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        if(position == 0) {
            holder.linearLayoutCompat.setBackgroundResource(R.drawable.bg_schedule_item_selected);
            int textColor = context.getResources().getColor(R.color.white);
            holder.txtName.setTextColor(textColor);
            holder.txtTime.setTextColor(textColor);
            holder.txtRoom.setTextColor(textColor);
            holder.txtTeacherName.setTextColor(textColor);
            holder.imgRoom.setColorFilter(textColor);
            holder.imgClock.setColorFilter(textColor);
        }
        holder.txtDay.setText(subject.getDay());
        holder.txtShift.setText(subject.getShift());
        holder.txtName.setText(subject.getName());
        holder.txtTime.setText(subject.getTime());
        holder.txtRoom.setText(subject.getRoom());
        holder.txtTeacherName.setText(subject.getTeacherName());
    }

    @Override
    public int getItemCount() {
        if(subjectList == null) return 0;
        else return subjectList.size();
    }

    public static class ScheduleStudyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtDay, txtShift, txtName, txtTime, txtRoom, txtTeacherName;
        public LinearLayoutCompat linearLayoutCompat;
        public ImageView imgClock, imgRoom;
        public ScheduleStudyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutCompat = itemView.findViewById(R.id.layoutItemSchedule);
            imgClock = itemView.findViewById(R.id.ic_clock);
            imgRoom = itemView.findViewById(R.id.ic_room);
            txtDay = itemView.findViewById(R.id.txtDay);
            txtShift = itemView.findViewById(R.id.txtCaHoc);
            txtName = itemView.findViewById(R.id.txtMonHoc);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtRoom = itemView.findViewById(R.id.txtLocation);
            txtTeacherName = itemView.findViewById(R.id.txtTeacher);
        }
    }
}