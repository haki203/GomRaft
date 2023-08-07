package com.example.gomraft.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gomraft.R;

import java.util.ArrayList;
import java.util.List;

public class FeedbackFragment extends Fragment {

    private ListView courseListView;
    private List<Course> courses;
    private ArrayList<String> answersList;
    private List<String> questionsList;
    private int currentQuestionIndex;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        currentQuestionIndex=0;
        courseListView = view.findViewById(R.id.courseListView);

        // Create sample course data
        courses = new ArrayList<>();
        courses.add(new Course("Android Networking", "MATH101", "John Doe"));
        courses.add(new Course("Lập trình web", "PHY101", "Jane Smith"));

        // Populate course names in the ListView
        List<String> courseNames = new ArrayList<>();
        for (Course course : courses) {
            courseNames.add(course.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, courseNames);
        courseListView.setAdapter(adapter);

        courseListView.setOnItemClickListener((adapterView, view1, position, l) -> showQuestionDialog(courses.get(position)));

        answersList = new ArrayList<>(); // Initialize the answers list

        return view;
    }

    private void showQuestionDialog(Course course) {
        // Initialize the questions list
        questionsList = new ArrayList<>();
        questionsList.add("Câu hỏi 1: Bạn thấy môn học này như thế nào?");
        questionsList.add("Câu hỏi 2: Bạn thấy cách giảng dạy của giảng viên như thế nào?");
        questionsList.add("Câu hỏi 3: Bạn thấy chất lượng bài giảng như thế nào?");
        questionsList.add("Câu hỏi 4: Bạn thấy cơ sở vật chất của trường như thế nào?");
        questionsList.add("Câu hỏi 5: Bạn hãy chấm điểm giảng viên");

        ArrayList<String> options = new ArrayList<>();
        options.add("Rất tệ");
        options.add("Tệ");
        options.add("Bình thường");
        options.add("Tốt");
        options.add("Rất tốt");
        options.add("Quá đỉnh");

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(questionsList.get(currentQuestionIndex))
                .setSingleChoiceItems(options.toArray(new String[0]), -1, null)
                .setPositiveButton("NEXT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog alertDialog = (AlertDialog) dialog;
                        int selectedPosition = alertDialog.getListView().getCheckedItemPosition();
                        if (selectedPosition != -1) {
                            answersList.add(options.get(selectedPosition));
                            if (currentQuestionIndex < questionsList.size() - 1) {
                                currentQuestionIndex++; // Move to the next question
                                showQuestionDialog(course); // Show the next question
                            } else {
                                // User has answered all questions, process answers as needed
                                // For example, log the answers
                                for (int i = 0; i < answersList.size(); i++) {
                                    String question = "Question " + (i + 1) + ": " + answersList.get(i);
                                    Log.d("FeedbackFragment", question);
                                }
                                // Clear the answers list and reset currentQuestionIndex for the next feedback
                                answersList.clear();
                                currentQuestionIndex = 0;
                                // Show a thank you dialog
                                showThankYouDialog("Cảm ơn bạn đã phản hồi","Câu trả lời của bạn đã được ghi nhận.");
                            }
                        }
                        else {
                            // User has not selected an answer, show a message to prompt them
                                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                                builder.setTitle("Thông báo")
                                        .setMessage("Bạn chưa chọn câu trả lời")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Do something if needed, or leave it empty to simply close the dialog

                                                showQuestionDialog(course);
                                            }
                                        })
                                        .show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
    private void showThankYouDialog(String title,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something if needed, or leave it empty to simply close the dialog
                    }
                })
                .show();
    }

    private class Course {
        private String name;
        private String code;
        private String lecturer;

        public Course(String name, String code, String lecturer) {
            this.name = name;
            this.code = code;
            this.lecturer = lecturer;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }

        public String getLecturer() {
            return lecturer;
        }
    }
}
