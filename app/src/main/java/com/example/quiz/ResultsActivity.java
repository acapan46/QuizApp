package com.example.quiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.quiz.R;
import com.example.quiz.model.Score;
import com.example.quiz.model.SharedPrefManager;
import com.example.quiz.model.User;
import com.example.quiz.remote.ApiUtils;
import com.example.quiz.remote.ScoreService;
import com.example.quiz.remote.UserService;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quiz.databinding.ActivityResultsBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsActivity extends AppCompatActivity {
    TextView tv, tv2, tv3;
    Button btnRestart;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        context = this;

        tv = (TextView)findViewById(R.id.tvres);
        tv2 = (TextView)findViewById(R.id.tvres2);
        tv3 = (TextView)findViewById(R.id.tvres3);
        btnRestart = (Button) findViewById(R.id.btnRestart);
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String date = sdf.format(calendar.getTime());

        StringBuffer sb = new StringBuffer();
        sb.append("Correct answers: " + QuizActivity.correct + "\n");
        StringBuffer sb2 = new StringBuffer();
        sb2.append("Wrong Answers: " + QuizActivity.wrong + "\n");
        StringBuffer sb3 = new StringBuffer();
        sb3.append("Final Score: " + QuizActivity.correct + "\n");
        tv.setText(sb);
        tv2.setText(sb2);
        tv3.setText(sb3);

        System.out.println(QuizActivity.wrong);
        System.out.println(QuizActivity.correct);


        String username = user.getUsername();
        int correct = QuizActivity.correct;
        int fullScore = (QuizActivity.correct + QuizActivity.wrong);
        String takenAt = date;

        Score score = new Score(username,correct,fullScore,takenAt);

        QuizActivity.correct=0;
        QuizActivity.wrong=0;
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScoreService scoreService = ApiUtils.getScoreService();
                //Call<Score> call = scoreService.addScore(user.getToken(),score);
                Call<Score> call = scoreService.addScore(user.getToken(),username,correct,fullScore,takenAt);
                // execute
                call.enqueue(new Callback<Score>() {
                    @Override
                    public void onResponse(Call<Score> call, Response<Score> response) {
                        // for debug purpose
                        Log.d("Results:", "Response: " + response.raw().toString());
                        // invalid session?
                        if (response.code() == 401)
                            displayAlert("Invalid session. Please re-login");
                        // user added successfully?
                        Score addedScore = response.body();
                        if (addedScore != null) {
                            // display message
                            Toast.makeText(context,
                                    addedScore.getUsername() + "'s score has been saved.",
                                    Toast.LENGTH_LONG).show();
                            // end this activity and forward user to BookListActivity
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            displayAlert("Add Score failed.");
                        }
                    }
                    @Override
                    public void onFailure(Call<Score> call, Throwable t) {
                        displayAlert("Error [" + t.getMessage() + "]");
                        // for debug purpose
                        Log.d("MyApp:", "Error: " + t.getCause().getMessage());
                    }
            });
                Intent in = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);

    }
    });
    }
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