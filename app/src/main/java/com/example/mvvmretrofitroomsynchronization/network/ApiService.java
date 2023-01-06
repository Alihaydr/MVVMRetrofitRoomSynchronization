package com.example.mvvmretrofitroomsynchronization.network;

import com.example.mvvmretrofitroomsynchronization.model.ListModule;
import com.example.mvvmretrofitroomsynchronization.model.QuoteModel;
import com.example.mvvmretrofitroomsynchronization.room.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("posts")
    Call<List<Quote>> getQuoteList();
    /*@POST("users")
    Call<GetModule> post_list(@Body Quote userRequest);*/
    @POST("task_data.php")
    Call<List<QuoteModel>> post_ids(@Body ListModule listModule);


}
