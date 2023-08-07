package com.example.gomraft.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.gomraft.R;

import java.util.ArrayList;

public class QuestionDialogFragment extends DialogFragment {

    private static final String ARG_QUESTION = "question";
    private static final String ARG_OPTIONS = "options";
    private String question;
    private ArrayList<String> options;
    private ArrayList<String> answers;
    private int currentQuestionIndex;
    // Định nghĩa interface để lắng nghe sự kiện câu trả lời
    public interface OnQuestionAnsweredListener {
        void onQuestionAnswered(ArrayList<String> answers);
    }
    private OnQuestionAnsweredListener listener;
    public static QuestionDialogFragment newInstance(String question, ArrayList<String> options) {
        QuestionDialogFragment fragment = new QuestionDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION, question);
        args.putStringArrayList(ARG_OPTIONS, options);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() != null) {
            question = getArguments().getString(ARG_QUESTION);
            options = getArguments().getStringArrayList(ARG_OPTIONS);
        }

        answers = new ArrayList<>();
        currentQuestionIndex = 0;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.question_layout, null);

        TextView questionTextView = view.findViewById(R.id.questionTextView);
        questionTextView.setText(question);

        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        for (String option : options) {
            RadioButton radioButton = new RadioButton(requireContext());
            radioButton.setText(option);
            radioGroup.addView(radioButton);
        }

        builder.setView(view)
                .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RadioButton selectedRadioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                        if (selectedRadioButton != null) {
                            answers.add(selectedRadioButton.getText().toString());
                            currentQuestionIndex++;
                            showNextQuestion();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

    private void showNextQuestion() {
        if (currentQuestionIndex < options.size()) {
            String question = "Question " + (currentQuestionIndex + 1) + ": " + options.get(currentQuestionIndex);
            TextView questionTextView = getDialog().findViewById(R.id.questionTextView);
            questionTextView.setText(question);
            RadioGroup radioGroup = getDialog().findViewById(R.id.radioGroup);
            radioGroup.clearCheck();
        }        // Khi người dùng đã trả lời hết tất cả các câu hỏi
        if (currentQuestionIndex == options.size()) {
            // Gửi kết quả trả lời về FeedbackFragment thông qua interface
            if (listener != null) {
                listener.onQuestionAnswered(answers);
            }
            dismiss();  // Đóng dialog sau khi đã trả lời hết tất cả câu hỏi
        }
    }
    // Phương thức để thiết lập OnQuestionAnsweredListener
    public void setOnQuestionAnsweredListener(OnQuestionAnsweredListener listener) {
        this.listener = listener;
    }
}
