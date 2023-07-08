package com.example.quiz.remote;

//import com.example.myapplication.model.Book;
//import com.example.quiz.model.DeleteResponse;
import com.example.quiz.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @FormUrlEncoded
    @POST("api/users/login")
    Call<User> login(@Field("username") String username, @Field("password") String
            password);

    @FormUrlEncoded
    @POST("api/users/login")
    Call<User> loginEmail(@Field("email") String email, @Field("password") String
            password);
}

