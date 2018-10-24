package com.eltendawy.newsapp.API;

import com.eltendawy.newsapp.API.Models.News;
import com.eltendawy.newsapp.API.Models.SourcesService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 9/28/2018.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */
public interface Services {

    @GET("/v2/sources")
    Call<SourcesService> getNewsSources(@Query("apiKey") String ApiKey, @Query("language")String lang);
    @GET("/v2/everything")
    Call<News> getNews(@Query("apiKey") String ApiKey, @Query("sources")String source, @Query("language")String lang,@Query("sortBy")String sortBy, @Query("page") int page);
    @GET("/v2/top-headlines")
    Call<News> getTopHeadlines(@Query("apiKey") String ApiKey,@Query("country") String country, @Query("sources")String source, @Query("language")String lang,@Query("sortBy")String sortBy, @Query("page") int page);
  }
