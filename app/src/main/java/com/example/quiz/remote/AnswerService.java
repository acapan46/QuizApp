package com.example.quiz.remote;

import com.example.quiz.model.Answer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AnswerService {
    @GET("api/answer/")
    Call<List<Answer>> getAllAnswer(@Header("api-key") String api_key);

    @GET("api/question/{id}")
    Call<Answer> getBook(@Header("api-key") String api_key, @Path("id") int id);

    /**
     * Add book by sending a single Book JSON
     * @return book object
     */
    @POST("api/question")
    Call<Answer> addBook(@Header ("api-key") String apiKey, @Body Answer a);

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
    Call<Answer> updateBook(@Header ("api-key") String apiKey, @Body Answer a);
}
