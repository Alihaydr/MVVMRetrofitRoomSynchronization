package com.example.mvvmretrofitroomsynchronization.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmretrofitroomsynchronization.model.QuoteModel;

import java.util.List;

@Dao
public interface QuoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(QuoteModel quote);

    @Update
    void update(QuoteModel quote);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllQuotes(List<QuoteModel> quote);

    @Delete
    void delete(QuoteModel quote);

    @Query("delete from quotes")
    void deleteAll();

    @Query("select * from quotes ORDER by quote_id desc")
    LiveData<List<QuoteModel>> getAllQuotes();

    @Query("select quote_id from quotes ORDER by quote_id asc")
    List<Integer> getAllIds();

    @Query("SELECT * FROM quotes WHERE quote_id = :id")
    LiveData<List<QuoteModel>> getSearchResult(int id);
}
