package com.nandawperdana.androidmvp.api;

import com.nandawperdana.androidmvp.api.people.PeopleResponse;
import com.nandawperdana.androidmvp.api.people.PeopleService;
import com.nandawperdana.androidmvp.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nandawperdana on 4/22/2016.
 */
public class APICallManager {
    public String endpoint = Constants.Path.DEFAULT_URL_API_PRODUCTION;
    public static APICallManager instance;
    private static Retrofit retrofit;

    public PeopleManager peopleManager;

    /**
     * singleton class instance
     *
     * @return APICallManager
     */
    public static APICallManager getInstance() {
        if (instance == null) {
            synchronized (APICallManager.class) {
                if (instance == null) {
                    instance = new APICallManager();
                }
            }
        }
        return instance;
    }

    public APICallManager() {
        // enable logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // registering API endpoint manager
        this.peopleManager = new PeopleManager();
    }

    public static <T> T getService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public class PeopleManager {
        PeopleService service;

        public PeopleManager() {
            this.service = getService(PeopleService.class);
        }

        public Call<PeopleResponse> getContacts() {
            return service.getPeople();
        }
    }
}
