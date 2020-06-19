package com.example.secondtask;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secondtask.data.Quiz;
import com.example.secondtask.data.QuizHistoryStorage;
import com.example.secondtask.data.QuizQuestion;
import com.example.secondtask.data.QuizScore;
import com.example.secondtask.data.QuizStorage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private ListView mQuestions;
    private QuestionArrayAdapter questionArrayAdapter;
    private String[] mAnswers;
    private ArrayList<QuizQuestion> quizQuestions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mQuestions = findViewById(R.id.questions);
        questionArrayAdapter = new QuestionArrayAdapter(this, 0, new ArrayList<QuizQuestion>());
        mQuestions.setAdapter(questionArrayAdapter);

        QuizStorage storage = new QuizStorage();
        Quiz quiz = storage.getQuizObject(this);

        if (quiz != null) {
            quizQuestions = quiz.getQuestions();
            questionArrayAdapter.addAll(quizQuestions);
            mAnswers = new String[quiz.getQuestions().size()];
        }

        findViewById(R.id.complete_quiz).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                completeQuiz();
            }
        });
    }

    public void completeQuiz() {
        int score = 0;

        for (QuizQuestion quizQuestion : quizQuestions) {
            if (quizQuestion.getCorrectAnswerNumber().equals(quizQuestion.getUserAnswerNumber())) {
                score++;
            }
        }
        Log.i("Score", score + "");
        saveScoreInHistory(score);
        Toast.makeText(this, String.format("Score is %d", score), Toast.LENGTH_SHORT).show();
        finish();
    }

    public void saveScoreInHistory(int score) {
        QuizScore quizScore = new QuizScore();
        quizScore.setScore(score);
        quizScore.setDate(Calendar.getInstance());
        QuizHistoryStorage storage = new QuizHistoryStorage();
        storage.add(this, quizScore);
        finish();
    }

    static class QuestionArrayAdapter extends ArrayAdapter<QuizQuestion> {

        private Context mContext;

        public QuestionArrayAdapter(@NonNull Context context, int resource, @NonNull List<QuizQuestion> objects) {
            super(context, resource, objects);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final QuizQuestion current = getItem(position);
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_question_item, parent, false);
            TextView textView = view.findViewById(R.id.question);
            textView.setText(current.getQuestion());
            List<String> options = current.getOptions();
            RadioGroup radioGroup = view.findViewById(R.id.radio_group);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton radioButton = group.findViewById(checkedId);
                    current.setUserAnswerNumber(Integer.toString(group.indexOfChild(radioButton)));
                }
            });
            RadioButton option1 = view.findViewById(R.id.answer_option1);
            option1.setText(options.get(0));
            RadioButton option2 = view.findViewById(R.id.answer_option2);
            option2.setText(options.get(1));
            RadioButton option3 = view.findViewById(R.id.answer_option3);
            option3.setText(options.get(2));
            RadioButton option4 = view.findViewById(R.id.answer_option4);
            option4.setText(options.get(3));
            return view;
        }
    }
}
