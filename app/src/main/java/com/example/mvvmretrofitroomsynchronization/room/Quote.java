package com.example.mvvmretrofitroomsynchronization.room;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "quotes")
public class Quote {

    @PrimaryKey
    private int quote_id;
    private String quote_name;
    private String quote_details;
    private String parent_id;
    private String language_id;
    private String status;
    private String is_favourite;
    private String created_at;
    private String updated_at;


    public Quote(int quote_id, String quote_name, String quote_details, String parent_id, String language_id, String status, String is_favourite, String created_at, String updated_at) {
        this.quote_id = quote_id;
        this.quote_name = quote_name;
        this.quote_details = quote_details;
        this.parent_id = parent_id;
        this.language_id = language_id;
        this.status = status;
        this.is_favourite = is_favourite;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    public String getQuote_name() {
        return quote_name;
    }

    public void setQuote_name(String quote_name) {
        this.quote_name = quote_name;
    }

    public String getQuote_details() {
        return quote_details;
    }

    public void setQuote_details(String quote_details) {
        this.quote_details = quote_details;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_favourite() {
        return is_favourite;
    }

    public void setIs_favourite(String is_favourite) {
        this.is_favourite = is_favourite;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
