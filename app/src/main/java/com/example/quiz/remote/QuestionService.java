package com.example.quiz.remote;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @GET("api/question/{id}")
    Call<Question> getBook(@Header("api-key") String api_key, @Path("id") int id);
    
    /**
     * Add book by sending a single Book JSON
     * @return book object
     */
    @POST("api/question")
    Call<qQuestion> addQuestion(@Header ("api-key") String apiKey, @Body qQuestion q);

    /**
     * Delete book based on the id
     * @return DeleteResponse object
     */
    //@POST("api/book/delete/{id}")
    //Call<DeleteResponse> deleteBook(@Header ("api-key") String apiKey, @Path("id")
    //int id);
    /**
     * Update book by sending a single Book JSON
     * @return book object
     */
    @POST("api/book/update")
    Call<Question> updateBook(@Header ("api-key") String apiKey, @Body Question q);
}
