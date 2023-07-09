package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz.model.SharedPrefManager;
import com.example.quiz.model.User;

public class AdminActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        context = this;

        // get reference to the textview
        TextView txtHello = findViewById(R.id.txtHello);

        // get user info from SharedPreferences
        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        // set the textview to display username
        txtHello.setText("Hello " + user.getUsername() + " !");

        // assign action to logout button
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // clear the shared preferences
                SharedPrefManager.getInstance(getApplicationContext()).logout();

                // display message
                Toast.makeText(getApplicationContext(),
                        "You have successfully logged out.",
                        Toast.LENGTH_LONG).show();

                // forward to LoginActivity
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

            }
        });

        // assign action to Book List button
        Button btnAddQuestion = findViewById(R.id.btnAddQuestion);
        btnAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AddQuestion.class);
                startActivity(intent);
            }
        });
    }


}