package com.example.mvvmretrofitroomsynchronization.model;


import java.util.List;

public class ListModule {


    List<Integer> quotes_id;

    public ListModule(List<Integer> quotes_id) {
        this.quotes_id = quotes_id;
    }

    public List<Integer> getList() {
        return quotes_id;
    }

    public void setList(List<Integer> quotes_id) {
        this.quotes_id = quotes_id;
    }
}
