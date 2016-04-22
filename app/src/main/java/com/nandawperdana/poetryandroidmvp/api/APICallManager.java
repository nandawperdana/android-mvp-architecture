package com.nandawperdana.poetryandroidmvp.api;

import com.nandawperdana.poetryandroidmvp.BuildConfig;
import com.nandawperdana.poetryandroidmvp.api.author.AuthorPoetsModel;
import com.nandawperdana.poetryandroidmvp.api.author.AuthorService;
import com.nandawperdana.poetryandroidmvp.api.author.AuthorsModel;
import com.nandawperdana.poetryandroidmvp.utils.Constants;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nandawperdana on 4/22/2016.
 */
public class APICallManager {
    public String mEndPoint = Constants.Path.DEFAULT_URL_API_STAGING;
    public static APICallManager mInstance;
    private static Retrofit mRetrofit;

    private final String mContentType = "application/json";
    private String authorizationKey;

    public AuthorManager authorManager;

    /**
     * singleton class instance
     *
     * @return APICallManager
     */
    public static APICallManager getInstance() {
        if (mInstance == null) {
            synchronized (APICallManager.class) {
                if (mInstance == null) {
                    mInstance = new APICallManager();
                }
            }
        }
        return mInstance;
    }

    public APICallManager() {
        if (BuildConfig.BUILD_RELEASE)
            mEndPoint = Constants.Path.DEFAULT_URL_API_PRODUCTION;

        // enable logging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(mEndPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        if (BuildConfig.BUILD_RELEASE)
            setAuthorizationKey(Constants.Keys.MASHAPE_KEYS_PRODUCTION);
        else
            setAuthorizationKey(Constants.Keys.MASHAPE_KEYS_TESTING);

        this.authorManager = new AuthorManager();
    }

    public static <T> T getService(Class<T> serviceClass) {
        return mRetrofit.create(serviceClass);
    }

    public String getAuthorizationKey() {
        return authorizationKey;
    }

    public void setAuthorizationKey(String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    public class AuthorManager {
        public Call<AuthorsModel> getAuthor() {
            AuthorService service = getService(AuthorService.class);
            return service.getAuthors(getAuthorizationKey(), mContentType);
        }

        public Call<List<AuthorPoetsModel>> getAuthorPoets(String authorName) {
            AuthorService service = getService(AuthorService.class);
            return service.getAuthorPoets(getAuthorizationKey(), mContentType, authorName);
        }

        public Call<List<AuthorPoetsModel>> getAuthorPoetsAbs(String authorName) {
            AuthorService service = getService(AuthorService.class);
            return service.getAuthorPoetsAbs(getAuthorizationKey(), mContentType, authorName);
        }
    }
}
