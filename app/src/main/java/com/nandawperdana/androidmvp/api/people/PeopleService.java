package com.nandawperdana.androidmvp.api.people;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nandawperdana on 1/18/2017.
 */

public interface PeopleService {
    @GET("/people.json")
    Call<PeopleResponse>
    getPeople();
}
