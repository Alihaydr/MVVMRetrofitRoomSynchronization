package com.example.mvvmretrofitroomsynchronization.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvmretrofitroomsynchronization.model.QuoteModel;


@androidx.room.Database(entities = {QuoteModel.class},version = 4)
public abstract class Database extends RoomDatabase {

    private static Database mInstance;

    public abstract QuoteDAO quoteDAO();

    public static synchronized Database getInstance(Context context){

        if (mInstance == null){

            mInstance = Room.databaseBuilder(context.getApplicationContext(),Database.class,"database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();


        }
        return mInstance;
    }

    private static Callback roomCallback = new Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDBAsyncTask(mInstance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void>{

        private QuoteDAO quoteDAO;

        private PopulateDBAsyncTask(Database db){

            quoteDAO = db.quoteDAO();

        }

        @Override
        protected Void doInBackground(Void... voids) {

          /*  categoriesDAO.insert(new News("www.google.com","Tech News: 5 Things to Know in Australia Today", R.drawable.iphone14,"www.google.com",1));
            categoriesDAO.insert(new News("www.pro.com","Business energy price cap gives startups breathing space", R.drawable.apple3,"www.facebook.com",2));
            categoriesDAO.insert(new News("www.amazon.com","Internet of Bodies: a data-led healthcare revolution", R.drawable.apple,"www.pro.com",3));

            inboxDao.insert(new Inbox("New Product in your Account","New Product in your Account lets see it",1));
            inboxDao.insert(new Inbox("Message From Admin","New Product in your Account lets see it",2));
            inboxDao.insert(new Inbox("Message From Mark","New Product in your Account lets see it",3));
*/

            return null;
        }
    }

}
