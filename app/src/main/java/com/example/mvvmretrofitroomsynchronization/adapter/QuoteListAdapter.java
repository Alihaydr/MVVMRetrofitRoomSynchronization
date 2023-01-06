package com.example.mvvmretrofitroomsynchronization.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvmretrofitroomsynchronization.R;
import com.example.mvvmretrofitroomsynchronization.model.QuoteModel;
import com.example.mvvmretrofitroomsynchronization.room.Quote;

import java.util.List;

public class QuoteListAdapter  extends RecyclerView.Adapter<QuoteListAdapter.MyViewHolder> {
    private Context context;
    private List<QuoteModel> quoteList;
    private ItemClickListener clickListener;

    public QuoteListAdapter(Context context, List<QuoteModel> quoteList) {
        this.context = context;
        this.quoteList = quoteList;
    }

    public void setQuoteList(List<QuoteModel> movieList) {
        this.quoteList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuoteListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteListAdapter.MyViewHolder holder,int position) {

        position = holder.getAdapterPosition();
        holder.tvTitle.setText(this.quoteList.get(position).getQuote_id()+" ");
        holder.txtbody.setText(this.quoteList.get(position).getQuote_name()+" ");

        int finalPosition = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // clickListener.onMovieClick(quoteList.get(finalPosition));
            }
        });

    }

    @Override
    public int getItemCount() {
        if(this.quoteList != null) {
            return this.quoteList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,txtuser,txtid,txtbody;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title_txt);
            txtbody = itemView.findViewById(R.id.body_txt);

        }
    }


    public interface ItemClickListener{
        public void onMovieClick(Quote movie);
    }
}
