package com.example.mvvmretrofitroomsynchronization.VM;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.mvvmretrofitroomsynchronization.MainActivity;
import com.example.mvvmretrofitroomsynchronization.model.QuoteModel;
import com.example.mvvmretrofitroomsynchronization.room.Quote;
import com.example.mvvmretrofitroomsynchronization.room.QuotesRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private QuotesRepository repository;
    private LiveData<List<QuoteModel>> allHome;
    private List<Integer> allIds;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository=new QuotesRepository(application);
        allHome= repository.getAllQuotes();
        allIds=repository.getAllIds();
    }

    public void insert(QuoteModel home){
        repository.insert(home);
    }

    public void insertAll(List<QuoteModel> quote){
        repository.insertAll(quote);
    }

    public void update(QuoteModel home){
        repository.update(home);
    }

    public void delete(QuoteModel home){
        repository.delete(home);
    }

    public void deleteAllInboxes(){
        repository.deleteAllNews();
    }

    public LiveData<List<QuoteModel>> getAllHome() {
        return allHome;
    }
    public List<Integer> getAllIds() {
        return allIds;
    }
    public LiveData<List<QuoteModel>> getSearchResult(int id) {
        return repository.getSearchQuotes(id);
    }

}
