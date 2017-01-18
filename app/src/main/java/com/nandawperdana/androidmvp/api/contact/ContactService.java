package com.nandawperdana.androidmvp.api.contact;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nandawperdana on 1/18/2017.
 */

public interface ContactService {
    @GET("/contacts")
    Call<ContactsModel>
    getContacts();
}
