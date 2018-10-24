package com.eltendawy.newsapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.eltendawy.newsapp.Adapters.SourcesAdapter;
import com.eltendawy.newsapp.Base.BaseActivity;
import com.eltendawy.newsapp.Interfaces.OnItemCLickListener;
import com.eltendawy.newsapp.R;
import com.eltendawy.newsapp.API.APIManager;
import com.eltendawy.newsapp.API.Models.SourcesService;
import com.eltendawy.newsapp.API.Models.Source;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    RecyclerView sources;
    SourcesAdapter adapter;
    List<Source> sourcesList;
    Switch selectLang;
    TextView langHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sources=findViewById(R.id.recycler);
        getSources();


    }

    public void getSources() {

        APIManager.getAPIS().getNewsSources(ApiKey,getLanguage()).enqueue(
                new Callback<SourcesService>() {
                    @Override
                    public void onResponse(Call<SourcesService> call, Response<SourcesService> response) {

                        sourcesList= response.body().getSources();
                        SetAdapter();
                    }

                    @Override
                    public void onFailure(Call<SourcesService> call, Throwable t) {

                    }
                }
        );
    }
    public void SetAdapter(){
        adapter=new SourcesAdapter(sourcesList);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        sources.setLayoutManager(manager);
        adapter.setOnItemCLickListener(new OnItemCLickListener() {
            @Override
            public void onClick(Object data, View view, int position) {
                Intent intent=new Intent(activity,ArticlesActivity.class);
                intent.putExtra(SOURCE_EXTRA,((Source)data));
                startActivity(intent);
            }
        });
        sources.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        final MenuItem switcBtn = menu.findItem(R.id.action_select_lang);
        View  view= switcBtn.getActionView();
        selectLang =view.findViewById(R.id.switch_lang);
        langHint = view.findViewById(R.id.hint);
        selectLang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    setLanguage("ar");
                    getSources();
                    langHint.setText(R.string.showing_arabic_only);
                }
                else
                {
                    setLanguage("");
                    getSources();
                    langHint.setText(R.string.showing_all_languages);
                }
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
