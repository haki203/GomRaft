package com.example.gomraft.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gomraft.Dto.ListScheduleSubjectResponseDTO;
import com.example.gomraft.R;
import com.example.gomraft.Model.Subject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ScheduleStudyAdapter extends RecyclerView.Adapter<ScheduleStudyAdapter.ScheduleStudyViewHolder> {
    List<ListScheduleSubjectResponseDTO.SubjectResponseDTO> subjectList;
    private Context context;
    public void setData(List<ListScheduleSubjectResponseDTO.SubjectResponseDTO> subjectList){
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
        ListScheduleSubjectResponseDTO.SubjectResponseDTO subject = subjectList.get(position);
        if(subject.getId() == subjectList.get(0).getId()) {
            holder.linearLayoutCompat.setBackgroundResource(R.drawable.bg_schedule_item_selected);
            int textColor = context.getResources().getColor(R.color.white);
            holder.txtName.setTextColor(textColor);
            holder.txtTime.setTextColor(textColor);
            holder.txtRoom.setTextColor(textColor);
            holder.txtTime.setTypeface(null, Typeface.BOLD);
            holder.txtName.setTypeface(null, Typeface.BOLD);
            holder.txtTeacherName.setTypeface(null, Typeface.BOLD);
            holder.txtRoom.setTypeface(null, Typeface.BOLD);
            holder.txtTeacherName.setTextColor(textColor);
            holder.imgRoom.setColorFilter(textColor);
            holder.imgClock.setColorFilter(textColor);
        }else{
            holder.linearLayoutCompat.setBackgroundResource(R.drawable.bg_schedule_item);
            int textColor = context.getResources().getColor(R.color.black);
            holder.txtName.setTextColor(textColor);
            holder.txtTime.setTextColor(textColor);
            holder.txtRoom.setTextColor(textColor);
            holder.txtTime.setTypeface(null, Typeface.NORMAL);
            holder.txtName.setTypeface(null, Typeface.BOLD);
            holder.txtTeacherName.setTypeface(null, Typeface.NORMAL);
            holder.txtRoom.setTypeface(null, Typeface.NORMAL);
            holder.txtTeacherName.setTextColor(textColor);
            holder.imgRoom.setColorFilter(textColor);
            holder.imgClock.setColorFilter(textColor);
        }
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM",Locale.getDefault());
        String outputDate = subject.getDay();
        try {
            Date date = inputDateFormat.parse(subject.getDay());
            if (date != null) {
                outputDate = outputDateFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.txtDay.setText(outputDate);
        holder.txtShift.setText(subject.getTime());
        holder.txtName.setText(subject.getCourse_name());
        holder.txtTime.setText(subject.getTime());
        holder.txtRoom.setText(subject.getRoom());
        holder.txtTeacherName.setText(subject.getTeacher_name());
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