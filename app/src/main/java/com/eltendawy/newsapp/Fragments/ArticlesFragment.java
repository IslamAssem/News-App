package com.eltendawy.newsapp.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eltendawy.newsapp.API.APIManager;
import com.eltendawy.newsapp.API.Models.ArticlesItem;
import com.eltendawy.newsapp.API.Models.News;
import com.eltendawy.newsapp.API.Models.Source;
import com.eltendawy.newsapp.Adapters.ArticlesRecyclerAdater;
import com.eltendawy.newsapp.Adapters.EndlessRecyclerViewScrollListener;
import com.eltendawy.newsapp.Base.BaseActivity;
import com.eltendawy.newsapp.Base.BaseFragment;
import com.eltendawy.newsapp.Interfaces.OnItemCLickListener;
import com.eltendawy.newsapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends BaseFragment {

    RecyclerView recycler;
    ArticlesRecyclerAdater adapter;
    LinearLayoutManager manager;
    Source source;
    String sort;
    int pageNumber;
    View view;
    public ArticlesFragment() {
        // Required empty public constructor
        pageNumber=0;
    }


    public ArticlesFragment setSource(Source source) {
        this.source = source;
        return this;
    }

    public ArticlesFragment setSort(String sort) {
        this.sort = sort;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_articles, container, false);
        initViews();
        return view;
    }


    private void initViews() {
        recycler=view.findViewById(R.id.recycler);
        adapter=new ArticlesRecyclerAdater(activity);
        manager=new LinearLayoutManager(activity);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
        if(source==null)return;
        adapter.setOnItemCLickListener(new OnItemCLickListener() {
            @Override
            public void onClick(Object data, View view, int position) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(((ArticlesItem)data).getUrl()));
                startActivity(intent);

            }
        });
        getArticles();
        recycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getArticles();
            }
        });
    }

    private void getArticles() {

        if (sort.equals("")) {
            APIManager.getAPIS().getTopHeadlines(activity.ApiKey,"eg", source.getId(), activity.getLanguage(), sort,pageNumber++ ).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    try
                    {
                        adapter.addArticle(response.body().getArticles());
                    }catch (Exception ignored)
                    {

                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
        } else {
            APIManager.getAPIS().getNews(activity.ApiKey, source.getId(), activity.getLanguage(), sort,pageNumber++).enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    try
                    {
                        adapter.addArticle(response.body().getArticles());
                    }catch (Exception ignored)
                    {
;
                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {

                }
            });
        }

    }
}
