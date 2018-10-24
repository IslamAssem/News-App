package com.eltendawy.newsapp.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected BaseActivity activity;
    protected Context context_application;
    public final String ApiKey="62b372f230254336ac0658e37362f250";
    public final String SOURCE_EXTRA="source";
    private static String language="ar";;
    private int pageNumber;


    public BaseActivity() {
        activity = this;
        pageNumber=1;

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPageNumber() {
        return pageNumber++;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context_application=getApplicationContext();


    }
}

