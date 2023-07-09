package com.example.quiz.remote;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.example.quiz.model.DeleteResponse;
import com.example.quiz.model.Question;
import com.example.quiz.model.qQuestion;
//import com.example.myapplication.model.DeleteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionService {

    @GET("api/question")
    Call<List<qQuestion>> getAllQuestion(@Header("api-key") String api_key);

    @GET("api/question/{qid}")
    Call<qQuestion> getQuestion(@Header("api-key") String api_key, @Path("qid") float qid);
    
    /**
     * Add score by sending a single Question JSON
     */
    @POST("api/question")
    Call<qQuestion> addQuestion(@Header ("api-key") String apiKey, @Body qQuestion q);

    /**
     * Delete question based on the id
     * @return DeleteResponse object
     */
    @POST("api/question/delete/{qid}")
    Call<DeleteResponse> deleteQuestion(@Header ("api-key") String apiKey, @Path("qid") float qid);
    /**
     * Update question by sending a single Book JSON
     */
    @POST("api/question/update")
    Call<qQuestion> updateQuestion(@Header ("api-key") String apiKey, @Body qQuestion question);

}
