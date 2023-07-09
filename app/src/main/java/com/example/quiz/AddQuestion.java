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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.model.SharedPrefManager;
import com.example.quiz.model.User;
import com.example.quiz.model.qQuestion;
import com.example.quiz.remote.ApiUtils;
import com.example.quiz.remote.QuestionService;
import com.example.quiz.remote.UserService;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuestion extends AppCompatActivity {

    private EditText txtQuestion;
    private EditText txtOption1;
    private EditText txtOption2;
    private EditText txtOption3;
    private EditText txtOption4;
    private EditText txtAnswer;
    private Context context;

    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        context = this;

        txtQuestion = findViewById(R.id.txtQuestion);
        txtOption1 = findViewById(R.id.txtOption1);
        txtOption2 = findViewById(R.id.txtOption2);
        txtOption3 = findViewById(R.id.txtOption3);
        txtOption4 = findViewById(R.id.txtOption4);
        txtAnswer = findViewById(R.id.txtAnswer);
        btnAdd = findViewById(R.id.btnAddq);

        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                String date = sdf.format(calendar.getTime());

                // get values in form
                int questionId = RandomNumberGenerator();
                String type = "general";
                int score = 1;
                String content = txtQuestion.getText().toString();
                String option1 = txtOption1.getText().toString();
                String option2 = txtOption2.getText().toString();
                String option3 = txtOption3.getText().toString();
                String option4 = txtOption4.getText().toString();
                String answer = txtAnswer.getText().toString();
                String dateCreated = date;

                qQuestion newQ = new qQuestion(0,questionId,0,type,1,score,dateCreated,"0000-00-00 00:00:00",content,option1,option2,option3,option4,answer);
                QuestionService questionService = ApiUtils.getQuestionService();
                Call<qQuestion> call = questionService.addQuestion(user.getToken(),newQ);

                call.enqueue(new Callback<qQuestion>() {

                    @Override
                    public void onResponse(Call<qQuestion> call, Response<qQuestion> response) {
                        // for debug purpose
                        Log.d("MyApp:", "Response: " + response.raw().toString());

                        // invalid session?
                        if (response.code() == 401)
                            displayAlert("Invalid session. Please re-login");

                        // user added successfully?
                        qQuestion addedQ = response.body();
                        if (addedQ != null) {
                            // display message
                            Toast.makeText(context,
                                    addedQ.getQid() + "question added successfully.",
                                    Toast.LENGTH_LONG).show();
                            // end this activity and forward user to BookListActivity
                            Intent intent = new Intent(context, AdminActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            displayAlert("Add New User failed.");
                        }
                    }

                    @Override
                    public void onFailure(Call<qQuestion> call, Throwable t) {
                        displayAlert("Error [" + t.getMessage() + "]");
                        // for debug purpose
                        Log.d("MyApp:", "Error: " + t.getCause().getMessage());
                    }
                });
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
    public int RandomNumberGenerator() {
        Random random = new Random();
        int randomNumber = 0;

        for (int i = 0; i < 13; i++) {
            randomNumber = random.nextInt();
        }
        return randomNumber;
    }
}
