package com.eltendawy.newsapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 9/28/2018.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */
public class APIManager {


    private static Retrofit retrofitInstance;

    private static Retrofit getInstance(){
        if(retrofitInstance==null){
            //build
             retrofitInstance = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofitInstance;
    }

    public static  Services getAPIS(){
       return getInstance().create(Services.class);
    }

}
