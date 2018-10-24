package com.eltendawy.newsapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableLayout;

import com.eltendawy.newsapp.Adapters.ArticlesRecyclerAdater;
import com.eltendawy.newsapp.Adapters.EndlessRecyclerViewScrollListener;
import com.eltendawy.newsapp.Adapters.SimpleFragmentPagerAdapter;
import com.eltendawy.newsapp.Base.BaseActivity;
import com.eltendawy.newsapp.Fragments.ArticlesFragment;
import com.eltendawy.newsapp.Interfaces.OnItemCLickListener;
import com.eltendawy.newsapp.R;
import com.eltendawy.newsapp.API.APIManager;
import com.eltendawy.newsapp.API.Models.ArticlesItem;
import com.eltendawy.newsapp.API.Models.News;
import com.eltendawy.newsapp.API.Models.Source;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesActivity extends BaseActivity {

    TabLayout tabLayout;
    ViewPager pager;
    SimpleFragmentPagerAdapter adapter;
    Source source;
    ArticlesFragment urgent,latest,mostRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        source=getIntent().getParcelableExtra(SOURCE_EXTRA);
        initViews();
    }

    private void initViews() {
        tabLayout=findViewById(R.id.tab_layout);
        pager=findViewById(R.id.view_pager);
        adapter=new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        latest=new ArticlesFragment().setSource(source).setSort("publishedAt");
        mostRead=new ArticlesFragment().setSource(source).setSort("popularity");
        urgent=new ArticlesFragment().setSource(source).setSort("");
        adapter.add(latest,"latest" );
        adapter.add(urgent,"urgent" );
        adapter.add(mostRead,"most read" );
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }
}
