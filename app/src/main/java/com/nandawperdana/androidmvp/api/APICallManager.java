package com.nandawperdana.androidmvp.api;

import com.nandawperdana.androidmvp.BuildConfig;
import com.nandawperdana.androidmvp.api.contact.ContactService;
import com.nandawperdana.androidmvp.api.contact.ContactsModel;
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
    public String mEndPoint = Constants.Path.DEFAULT_URL_API_STAGING;
    public static APICallManager mInstance;
    private static Retrofit sRetrofit;

    private final String mContentType = "application/json";
    private String authorizationKey;

    public ContactManager contactManager;

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

        sRetrofit = new Retrofit.Builder()
                .baseUrl(mEndPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // registering API endpoint manager
        this.contactManager = new ContactManager();
    }

    public static <T> T getService(Class<T> serviceClass) {
        return sRetrofit.create(serviceClass);
    }

    public String getAuthorizationKey() {
        return authorizationKey;
    }

    public void setAuthorizationKey(String authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    public class ContactManager {
        public Call<ContactsModel> getContacts() {
            ContactService service = getService(ContactService.class);
            return service.getContacts();
        }
    }
}
