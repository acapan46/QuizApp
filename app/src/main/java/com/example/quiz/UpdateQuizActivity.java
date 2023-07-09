package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.model.SharedPrefManager;
import com.example.quiz.model.User;
import com.example.quiz.model.qQuestion;
import com.example.quiz.remote.ApiUtils;
import com.example.quiz.remote.QuestionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateQuizActivity extends AppCompatActivity {

    private QuestionService questionService;
    private qQuestion question;      // store book info

    // form fields
    private EditText txtQuestion;
    private EditText txtOption1;
    private EditText txtOption2;
    private EditText txtOption3;
    private EditText txtOption4;
    private EditText txtAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_quiz);

        // retrieve book id from intent
        // get book id sent by BookListActivity, -1 if not found
        Intent intent = getIntent();
        float qid = intent.getFloatExtra("qid", -1);

        // get references to the form fields in layout
        txtQuestion = findViewById(R.id.upQuestion);
        txtOption1 = findViewById(R.id.upOption1);
        txtOption2 = findViewById(R.id.upOption2);
        txtOption3 = findViewById(R.id.upOption3);
        txtOption4 = findViewById(R.id.upOption4);
        txtAnswer = findViewById(R.id.upAnswer);

        // retrieve book info from database using the book id
        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // get book service instance
        questionService = ApiUtils.getQuestionService();

        // execute the API query. send the token and book id
        questionService.getQuestion(user.getToken(), qid).enqueue(new Callback<qQuestion>() {
            @Override
            public void onResponse(Call<qQuestion> call, Response<qQuestion> response) {
                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // get book object from response
                question = response.body();

                // set values into forms
                txtQuestion.setText(question.getContent());
                txtOption1.setText(question.getOption1());
                txtOption2.setText(question.getOption2());
                txtOption3.setText(question.getOption3());
                txtOption4.setText(question.getOption4());
                txtAnswer.setText(question.getCorrect());
            }

            @Override
            public void onFailure(Call<qQuestion> call, Throwable t) {
                Toast.makeText(null, "Error connecting", Toast.LENGTH_LONG).show();
            }
        });
    }
    /**
     * Update question info in database when the user click Update Question button
     */
    public void updateQuestion(View view) {
        // get values in form
        String content = txtQuestion.getText().toString();
        String option1 = txtOption1.getText().toString();
        String option2 = txtOption2.getText().toString();
        String option3 = txtOption2.getText().toString();
        String option4 = txtOption3.getText().toString();
        String answer = txtAnswer.getText().toString();

        // initialize updatedAt to today's date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String date = sdf.format(calendar.getTime());
        String updatedAt = date;

        // update the quesiton object retrieved in onCreate with the new data.
        question.setContent(content);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setCorrect(answer);
        question.setUpdatedAt(updatedAt);

        Log.d("MyApp:", "Question info: " + question.toString());

        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // send request to update the question record to the REST API
        QuestionService questionService = ApiUtils.getQuestionService();
        Call<qQuestion> call = questionService.updateQuestion(user.getToken(), question);

        Context context = this;
        // execute
        call.enqueue(new Callback<qQuestion>() {
            @Override
            public void onResponse(Call<qQuestion> call, Response<qQuestion> response) {

                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // invalid session?
                if (response.code() == 401)
                    displayAlert("Invalid session. Please re-login");

                // question updated successfully?
                qQuestion updatedQuestion = response.body();
                if (updatedQuestion != null) {
                    // display message
                    Toast.makeText(context,
                            updatedQuestion.getQid() + "question updated successfully.",
                            Toast.LENGTH_LONG).show();

                    // end this activity and forward user to BookListActivity
                    Intent intent = new Intent(context, AdminActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    displayAlert("Update Question failed.");
                }
            }

            @Override
            public void onFailure(Call<qQuestion> call, Throwable t) {
                displayAlert("Error [" + t.getMessage() + "]");
                // for debug purpose
                Log.d("UpdateSection:", "Error: " + t.getCause().getMessage());
            }
        });
    }

    /**
     * Displaying an alert dialog with a single button
     * @param message - message to be displayed
     */
    public void displayAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}