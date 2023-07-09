package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.model.Answer;
import com.example.quiz.model.ErrorResponse;
import com.example.quiz.model.Question;
import com.example.quiz.model.SharedPrefManager;
import com.example.quiz.model.User;
import com.example.quiz.model.qQuestion;
import com.example.quiz.remote.AnswerService;
import com.example.quiz.remote.ApiUtils;
import com.example.quiz.remote.QuestionService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.quiz.remote.UserService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    Context context = this;

    QuestionService questionService;
    AnswerService answerService;
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    String questions[] = {
            "Which method can be defined only once in a program?",
            "Which of these is not a bitwise operator?",
            "Which keyword is used by method to refer to the object that invoked it?",
            "Which of these keywords is used to define interfaces in Java?",
            "Which of these access specifiers can be used for an interface?",
            "Which of the following is correct way of importing an entire package ‘pkg’?",
            "What is the return type of Constructors?",
            "Which of the following package stores all the standard java classes?",
            "Which of these method of class String is used to compare two String objects for their equality?",
            "An expression involving byte, int, & literal numbers is promoted to which of these?"
    };
    String answers[] = {"main method","<=","this","interface","public","import pkg.*","None of the mentioned","java","equals()","int"};
    String opt[] = {
            "finalize method","main method","static method","private method",
            "&","&=","|=","<=",
            "import","this","catch","abstract",
            "Interface","interface","intf","Intf",
            "public","protected","private","All of the mentioned",
            "Import pkg.","import pkg.*","Import pkg.*","import pkg.",
            "int","float","void","None of the mentioned",
            "lang","java","util","java.packages",
            "equals()","Equals()","isequal()","Isequal()",
            "int","long","byte","float"
    };
    List<qQuestion> question = new ArrayList<qQuestion>();

    public static int marks=0,correct=0,wrong=0;

    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        context = this;

        final TextView score = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        if (name.trim().equals(""))
            textView.setText("Hello User");
        else
            textView.setText("Hello " + name);

        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvQuestion);

        radio_g=(RadioGroup)findViewById(R.id.radioGroupOptions);
        rb1=(RadioButton)findViewById(R.id.rbOption1);
        rb2=(RadioButton)findViewById(R.id.rbOption2);
        rb3=(RadioButton)findViewById(R.id.rbOption3);
        rb4=(RadioButton)findViewById(R.id.rbOption4);

        questionService = ApiUtils.getQuestionService();
        answerService = ApiUtils.getAnswerService();
        System.out.println("before response");
        questionService.getAllQuestion(user.getToken()).enqueue(new Callback<List<qQuestion>>(){
            @Override
            public void onResponse(Call<List<qQuestion>> call, Response<List<qQuestion>> response) {
                System.out.println("in response");
                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                if(response.isSuccessful()){
                    // Get list of book object from response
                    question = response.body();

                    tv.setText(question.get(flag).getContent());
                    rb1.setText(question.get(0).getOption1());
                    rb2.setText(question.get(0).getOption2());
                    rb3.setText(question.get(0).getOption3());
                    rb4.setText(question.get(0).getOption4());

                    submitbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //int color = mBackgroundColor.getColor();
                            //mLayout.setBackgroundColor(color);

                            if(radio_g.getCheckedRadioButtonId()==-1)
                            {
                                Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                            String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                            if(ansText.equals(question.get(flag).getCorrect())) {
                                correct++;
                                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                wrong++;
                                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                            }

                            flag++;

                            if (score != null)
                                score.setText(""+correct);

                            if(flag < question.size())
                            {
                                tv.setText(question.get(flag).getContent());
                                rb1.setText(question.get(flag).getOption1());
                                rb2.setText(question.get(flag).getOption2());
                                rb3.setText(question.get(flag).getOption3());
                                rb4.setText(question.get(flag).getOption4());
                            }
                            else
                            {
                                marks=correct;
                                Intent in = new Intent(getApplicationContext(),ResultsActivity.class);
                                startActivity(in);
                            }
                            radio_g.clearCheck();
                        }
                    });


                }
                else if (response.errorBody() != null){
                    // parse response to POJO
                    String errorResp = null;
                    try {
                        errorResp = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ErrorResponse e = new Gson().fromJson( errorResp,
                            ErrorResponse.class);
                    displayToast(e.getError().getMessage());
                }
            }
            @Override
            public void onFailure(Call<List<qQuestion>> call, Throwable t) {
                displayToast("Error connecting to server.");
                displayToast(t.getMessage());
            }
        });

        System.out.println("out response");

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ResultsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void displayToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
