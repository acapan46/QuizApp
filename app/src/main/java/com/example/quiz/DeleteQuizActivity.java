package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quiz.adapter.QuestionAdapter;
import com.example.quiz.model.DeleteResponse;
import com.example.quiz.model.SharedPrefManager;
import com.example.quiz.model.User;
import com.example.quiz.model.qQuestion;
import com.example.quiz.remote.ApiUtils;
import com.example.quiz.remote.QuestionService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteQuizActivity extends AppCompatActivity {

    QuestionService questionService;
    Context context;
    RecyclerView questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_quiz);
        context = this; // get current activity context

        // get reference to the RecyclerView bookList
        questionList = findViewById(R.id.questionList);

        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // get book service instance
        questionService = ApiUtils.getQuestionService();

        // execute the call. send the user token when sending the query
        questionService.getAllQuestion(user.getToken()).enqueue(new Callback<List<qQuestion>>() {
            @Override
            public void onResponse(Call<List<qQuestion>> call, Response<List<qQuestion>> response) {
                // for debug purpose
                Log.d("MyApp:", "Response: " + response.raw().toString());

                // Get list of book object from response
                List<qQuestion> questions = response.body();

                // initialize adapter
                QuestionAdapter adapter = new QuestionAdapter(context, questions);

                // set adapter to the RecyclerView
                questionList.setAdapter(adapter);

                // set layout to recycler view
                questionList.setLayoutManager(new LinearLayoutManager(context));

                // add separator between item in the list
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(questionList.getContext(),
                        DividerItemDecoration.VERTICAL);
                questionList.addItemDecoration(dividerItemDecoration);
            }

            @Override
            public void onFailure(Call<List<qQuestion>> call, Throwable t) {
                Toast.makeText(context, "Error connecting to the server", Toast.LENGTH_LONG).show();
                Log.e("MyApp:", t.getMessage());
            }
        });
    }
}