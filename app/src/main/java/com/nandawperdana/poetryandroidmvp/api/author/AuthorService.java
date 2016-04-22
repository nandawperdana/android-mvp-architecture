package com.nandawperdana.poetryandroidmvp.api.author;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by nandawperdana on 4/22/2016.
 * This is service class, used for call the endpoint API
 */
public interface AuthorService {
    @GET("/author")
    Call<AuthorsModel>
    getAuthors(@Header("X-Mashape-Key") String mashapeKey,
               @Header("Accept") String accept);

    @GET("/author/{author}")
    Call<List<AuthorPoetsModel>>
    getAuthorPoets(@Header("X-Mashape-Key") String mashapeKey,
                   @Header("Accept") String accept,
                   @Path("author") String author);

    @GET("/author/{author}:abs")
    Call<List<AuthorPoetsModel>>
    getAuthorPoetsAbs(@Header("X-Mashape-Key") String mashapeKey,
                      @Header("Accept") String accept,
                      @Path("author") String author);
}
