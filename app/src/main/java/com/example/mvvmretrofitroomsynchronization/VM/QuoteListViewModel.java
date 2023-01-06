package com.example.mvvmretrofitroomsynchronization.VM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmretrofitroomsynchronization.model.QuoteModel;
import com.example.mvvmretrofitroomsynchronization.network.ApiService;
import com.example.mvvmretrofitroomsynchronization.network.RetroInstance;
import com.example.mvvmretrofitroomsynchronization.room.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteListViewModel extends ViewModel {

    private MutableLiveData<List<Quote>> moviesList;

    public QuoteListViewModel(){
        moviesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Quote>> getMoviesListObserver() {
        return moviesList;

    }

    public void makeApiCall() {
        ApiService apiService = RetroInstance.getRetroClient().create(ApiService.class);
        Call<List<Quote>> call = apiService.getQuoteList();
        call.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {

                moviesList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                moviesList.postValue(null);

            }
        });
    }



}