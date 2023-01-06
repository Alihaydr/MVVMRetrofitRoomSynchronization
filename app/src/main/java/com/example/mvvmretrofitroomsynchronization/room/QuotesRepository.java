package com.example.mvvmretrofitroomsynchronization.room;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.mvvmretrofitroomsynchronization.MainActivity;
import com.example.mvvmretrofitroomsynchronization.model.QuoteModel;

import java.util.List;

public class QuotesRepository {


    private QuoteDAO quoteDAO;
    private LiveData<List<QuoteModel>> allQuotes;
    private LiveData<List<QuoteModel>> SearchQuotes;
     private List<Integer> allIds;
    public static ProgressDialog myDialog;
    public Context context;
    public QuotesRepository(Application application)
    {


        Database database = Database.getInstance(application);

        quoteDAO = database.quoteDAO();
        allQuotes= quoteDAO.getAllQuotes();
        allIds= quoteDAO.getAllIds();

    }

    public void insert(QuoteModel quote){

        new InsertHomeAsyncTask(quoteDAO).execute(quote);

    }
    public void update(QuoteModel quote){
        new UpdateHomeAsyncTask(quoteDAO).execute(quote);


    }
    public void delete(QuoteModel quote){
        new DeleteHomeAsyncTask(quoteDAO).execute(quote);


    }

    public void insertAll(List<QuoteModel> quote){
        this.context=context;
        new InsertAllAsyncTask(quoteDAO).execute(quote);


    }

    public void deleteAllNews(){

        new DeleteAllAsyncTask(quoteDAO).execute();

    }
    public LiveData<List<QuoteModel>> getSearchQuotes(int key) {
        return quoteDAO.getSearchResult(key);
    }
    public LiveData<List<QuoteModel>> getAllQuotes() {
        return allQuotes;
    }

    public List<Integer> getAllIds() {
        return allIds;
    }
   /* public List<Integer> getAllIds() {
        return allIds;
    }
*/

    private static class UpdateHomeAsyncTask extends AsyncTask<QuoteModel,Void,Void>{


        private QuoteDAO quoteDAO;

        private UpdateHomeAsyncTask(QuoteDAO quoteDAO){

            this.quoteDAO=quoteDAO;
        }
        @Override
        protected Void doInBackground(QuoteModel... quotes) {

            quoteDAO.update(quotes[0]);
            return null;
        }
    }


    private static class InsertHomeAsyncTask extends AsyncTask<QuoteModel,Void,Void>{


        private QuoteDAO quoteDAO;

        private InsertHomeAsyncTask(QuoteDAO quoteDAO){

            this.quoteDAO=quoteDAO;
        }
        @Override
        protected Void doInBackground(QuoteModel... quotes) {

            quoteDAO.insert(quotes[0]);
            return null;
        }
    }

    private static class InsertAllAsyncTask extends AsyncTask<List<QuoteModel>,Integer,Void> {


        private QuoteDAO quoteDAO;
        Context context;

        private InsertAllAsyncTask(QuoteDAO quoteDAO) {

            this.quoteDAO = quoteDAO;
            this.context = context;
        }

        @Override
        protected Void doInBackground(List<QuoteModel>... quotes) {

            quoteDAO.insertAllQuotes(quotes[0]);
            return null;
        }



    }

        private static class DeleteHomeAsyncTask extends AsyncTask<QuoteModel, Void, Void> {


            private QuoteDAO quoteDAO;

            private DeleteHomeAsyncTask(QuoteDAO quoteDAO) {

                this.quoteDAO = quoteDAO;
            }

            @Override
            protected Void doInBackground(QuoteModel... quotes) {

                quoteDAO.delete(quotes[0]);
                return null;
            }
        }


        private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {


            private QuoteDAO quoteDAO;

            private DeleteAllAsyncTask(QuoteDAO quoteDAO) {

                this.quoteDAO = quoteDAO;
            }

            @Override
            protected Void doInBackground(Void... voids) {

                quoteDAO.deleteAll();
                return null;
            }
        }

}
