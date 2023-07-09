package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.quiz.adapter.QuestionAdapter;
import com.example.quiz.model.SharedPrefManager;
import com.example.quiz.model.User;
import com.example.quiz.model.qQuestion;
import com.example.quiz.remote.ApiUtils;
import com.example.quiz.remote.QuestionService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateView extends AppCompatActivity {

    QuestionService questionService;
    Context context;
    RecyclerView questionList;

    QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_quiz);
        context = this; // get current activity context

        // get reference to the RecyclerView bookList
        questionList = findViewById(R.id.questionList);

        //start for context menu when longclick
        registerForContextMenu(questionList);

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
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.question_context_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        qQuestion selectedQuestion = adapter.getSelectedItem();
        Log.d("MyApp", "selected "+selectedQuestion.toString());

        if(item.getItemId() == R.id.menu_update){
            doUpdate(selectedQuestion);
        }
        return super.onContextItemSelected(item);
    }

    private void doUpdate(qQuestion selectedBook) {
        // for debugging purpose
        Log.d("MyApp:", "launching update activity for "+selectedBook.toString());
        // launch UpdateBookActivity and pass the book id
        Intent intent = new Intent(context, UpdateQuizActivity.class);
        intent.putExtra("qid", selectedBook.getQid());
        startActivity(intent);
    }
}