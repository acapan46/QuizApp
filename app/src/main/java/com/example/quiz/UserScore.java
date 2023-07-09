package com.example.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quiz.adapter.ScoreAdapter;
import com.example.quiz.model.Score;
import com.example.quiz.model.SharedPrefManager;
import com.example.quiz.model.User;
import com.example.quiz.remote.ApiUtils;
import com.example.quiz.remote.ScoreService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserScore extends AppCompatActivity {
    ScoreService scoreService;
    Context context;
    RecyclerView scoreList;
    ScoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_score);
        context = this; // get current activity context

        // get reference to the RecyclerView bookList
        scoreList = findViewById(R.id.scoreList);

        //register for context menu
        registerForContextMenu(scoreList);

        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // get book service instance
        scoreService = ApiUtils.getScoreService();

        // execute the call. send the user token when sending the query
        scoreService.getAllScore(user.getToken()).enqueue(new Callback<List<Score>>() {
            @Override
            public void onResponse(Call<List<Score>> call, Response<List<Score>> response) {
                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // Get list of score object from response
                List<Score> scores = response.body();

                // initialize adapter
                adapter = new ScoreAdapter(context, scores);

                // set adapter to the RecyclerView
                scoreList.setAdapter(adapter);

                // set layout to recycler view
                scoreList.setLayoutManager(new LinearLayoutManager(context));

                // add separator between item in the list
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(scoreList.getContext(),
                        DividerItemDecoration.VERTICAL);
                scoreList.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onFailure(Call<List<Score>> call, Throwable t) {
                Toast.makeText(context, "Error connecting to the server", Toast.LENGTH_LONG).show();
                Log.e("MyApp:", t.getMessage());
            }
        });

        // action handler for Add Book floating button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // forward user to NewBookActivity
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Fetch data for ListView
     */
    private void updateListView() {
        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // get score service instance
        scoreService = ApiUtils.getScoreService();

        // execute the call. send the user token when sending the query
        scoreService.getAllScore(user.getToken()).enqueue(new Callback<List<Score>>() {
            @Override
            public void onResponse(Call<List<Score>> call, Response<List<Score>> response) {
                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // token is not valid/expired
                if (response.code() == 401) {
                    displayAlert("Session Invalid");
                }

                // Get list of score object from response
                List<Score> scores = response.body();

                // initialize adapter
                adapter = new ScoreAdapter(context, scores);

                // set adapter to the RecyclerView
                scoreList.setAdapter(adapter);

                // set layout to recycler view
                scoreList.setLayoutManager(new LinearLayoutManager(context));

                // add separator between item in the list
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(scoreList.getContext(),
                        DividerItemDecoration.VERTICAL);
                scoreList.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onFailure(Call<List<Score>> call, Throwable t) {
                Toast.makeText(context, "Error connecting to the server", Toast.LENGTH_LONG).show();
                displayAlert("Error [" + t.getMessage() + "]");
                Log.e("MyApp:", t.getMessage());
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