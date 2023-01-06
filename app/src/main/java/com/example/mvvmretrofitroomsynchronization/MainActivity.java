package com.example.mvvmretrofitroomsynchronization;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvmretrofitroomsynchronization.VM.HomeViewModel;
import com.example.mvvmretrofitroomsynchronization.VM.QuoteListViewModel;
import com.example.mvvmretrofitroomsynchronization.adapter.QuoteListAdapter;
import com.example.mvvmretrofitroomsynchronization.model.ListModule;
import com.example.mvvmretrofitroomsynchronization.model.QuoteModel;
import com.example.mvvmretrofitroomsynchronization.network.ApiService;
import com.example.mvvmretrofitroomsynchronization.network.RetroInstance;
import com.example.mvvmretrofitroomsynchronization.room.Quote;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    //private List<Quote> movieModelList;
    private QuoteListAdapter adapter;
    private QuoteListViewModel getdatafromapiviewModel;
    private HomeViewModel quoteViewModel;
    private Quote quote_l;
    ProgressDialog progressDialog;
    List<Integer> FirstList;
    public RecyclerView recyclerView;
    EditText textInputEditText;
 //   private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler);
        textInputEditText = findViewById(R.id.edit_search);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        quoteViewModel = new ViewModelProvider(MainActivity.this).get(HomeViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Please wait to get quotes");
        progressDialog.setTitle("Searching...");
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.show();



       // quoteViewModel.deleteAllInboxes();


    //post ids list to api and receive all missing quotes

        postUsers(quoteViewModel.getAllIds());


        textInputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String query = textInputEditText.getText().toString();
                    search(Integer.parseInt(query));
                    return true;
                }
                return false;
            }
        });


    }

    private void search(int query) {
        quoteViewModel.getSearchResult(query).observe(this, new Observer<List<QuoteModel>>() {
            @Override
            public void onChanged(List<QuoteModel> quoteModel) {
                adapter.setQuoteList(quoteModel);
                recyclerView.setAdapter(adapter);

            }
        });
    }

    public void postUsers(List<Integer> List) {

        ApiService apiService = RetroInstance.getRetroClient().create(ApiService.class);

         ArrayList<Integer> arrayList = (ArrayList<Integer>) List;

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 150; i++) {
            list.add(i);

        }
        if (arrayList.isEmpty())
            arrayList.add(0);

        Toast.makeText(this, arrayList.get(arrayList.size()-1)+"", Toast.LENGTH_SHORT).show();

        ListModule listModule= new ListModule(arrayList);


        Call<List<QuoteModel>> call = apiService.post_ids(listModule);

        call.enqueue(new Callback<List<QuoteModel>>() {
            @Override
            public void onResponse(Call<List<QuoteModel>> call, Response<List<QuoteModel>> response) {
                Log.v("SUCCESS", response.body() + " ");
                if (response.isSuccessful()) {
                    List<QuoteModel> getList = response.body();

                    if(getList.get(0).getQuote_id()==-9090){
                        quoteViewModel.getAllHome().observe(MainActivity.this, new Observer<List<QuoteModel>>() {
                            @Override
                            public void onChanged(List<QuoteModel> homes) {

                                if (homes != null) {
                                    adapter = new QuoteListAdapter(getApplicationContext(), homes);
                                }
                                recyclerView.setAdapter(adapter);

                            }
                        });

                        Toast.makeText(MainActivity.this, "Synchronized !", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }else {

                        try {

                                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
                                quoteViewModel.insertAll(getList);

                                quoteViewModel.getAllHome().observe(MainActivity.this, new Observer<List<QuoteModel>>() {
                                    @Override
                                    public void onChanged(List<QuoteModel> homes) {
                                        // int i = 0;

                                        if (homes != null) {
                                            adapter = new QuoteListAdapter(getApplicationContext(), homes);
                                        }
                                        recyclerView.setAdapter(adapter);
                                        //Toast.makeText(MainActivity.this, ++i+"", Toast.LENGTH_SHORT).show();

                                    }
                                });

                                progressDialog.dismiss();

                        } catch (Exception e) {

                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                }



            }

            @Override
            public void onFailure(Call<List<QuoteModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}