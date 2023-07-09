package com.example.quiz.remote;

public class ApiUtils {
    // REST API server URL
    public static final String BASE_URL =
            "https://proj557.000webhostapp.com/prestige/";
    // return UserService instance
    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
    public static QuestionService getQuestionService(){
        return RetrofitClient.getClient(BASE_URL).create(QuestionService.class);
    }
    public static AnswerService getAnswerService(){
        return RetrofitClient.getClient(BASE_URL).create(AnswerService.class);
    }
    public static ScoreService getScoreService(){
        return RetrofitClient.getClient(BASE_URL).create(ScoreService.class);
    }
    // return BookService instance
    //public static BookService getBookService() {
        //return RetrofitClient.getClient(BASE_URL).create(BookService.class);
    //}
}

