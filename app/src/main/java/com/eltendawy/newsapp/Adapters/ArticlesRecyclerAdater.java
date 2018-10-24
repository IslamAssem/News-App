package com.eltendawy.newsapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eltendawy.newsapp.Interfaces.OnItemCLickListener;
import com.eltendawy.newsapp.R;
import com.eltendawy.newsapp.API.Models.ArticlesItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticlesRecyclerAdater extends RecyclerView.Adapter<ArticlesRecyclerAdater.ViewHolder> {

    Context context;
    ArrayList<ArticlesItem> articles;
    OnItemCLickListener onItemCLickListener;
    private int newItemPosition;

    public ArticlesRecyclerAdater(Context context) {
        this.context = context;
        articles=new ArrayList<>(20);
        newItemPosition =0;
    }

    public void addArticle(ArticlesItem article)
    {
        newItemPosition =articles.size();
        articles.add(article);
        notifyItemChanged(newItemPosition);
    }
    public void addArticle(List<ArticlesItem> articles)
    {
        if(articles==null)
            return;
        newItemPosition =articles.size();
        this.articles.addAll(articles);
        notifyItemRangeChanged(newItemPosition, articles.size());
    }

    public void setOnItemCLickListener(OnItemCLickListener onItemCLickListener) {
        this.onItemCLickListener = onItemCLickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_item_news_card,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.sourceName.setText(articles.get(position).getSource().getName());
        holder.articleTitle.setText(articles.get(position).getTitle());
        holder.articleDescription.setText(articles.get(position).getDescription());
        holder.articleTime.setText(articles.get(position).getPublishedAt());
        holder.fav.setImageResource(R.drawable.star_off);
        Picasso.with(context)
                .load(articles.get(position).getUrlToImage())
                .error(R.drawable.error)
                .into(holder.articleImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCLickListener.onClick(articles.get(holder.getAdapterPosition()), v, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(articles==null)
            articles=new ArrayList<>();
        return articles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView sourceName,articleTitle,articleDescription,articleTime,btnShare,btnComment;
        ImageView sourceLogo,articleImage,fav;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            sourceName=itemView.findViewById(R.id.source_name);
            articleTitle=itemView.findViewById(R.id.article_title);
            articleDescription=itemView.findViewById(R.id.article_descroption);
            articleTime=itemView.findViewById(R.id.article_time);
            btnShare=itemView.findViewById(R.id.btn_share);
            btnComment=itemView.findViewById(R.id.btn_comment);
            sourceLogo=itemView.findViewById(R.id.source_logo);
            articleImage=itemView.findViewById(R.id.article_image);
            fav=itemView.findViewById(R.id.article_fav);
        }
    }
}
