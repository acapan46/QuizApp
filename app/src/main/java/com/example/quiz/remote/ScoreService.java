package com.example.quiz.remote;

//import com.example.myapplication.model.Book;
//import com.example.quiz.model.DeleteResponse;
import com.example.quiz.model.Score;
import com.example.quiz.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ScoreService {
    @FormUrlEncoded
    @POST("api/score")
    Call<Score> addScore(@Header("api-key") String apiKey, @Field("id") int id, @Field("username") String username,@Field("correct") int correct, @Field("fullScore") int fullScore, @Field("takenAt") String takenAt);

    @GET("api/score/?order=correct&orderType=DESC")
    Call<List<Score>>getAllScore(@Header("api-key") String apiKey);
}

