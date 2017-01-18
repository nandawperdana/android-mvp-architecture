package com.nandawperdana.androidmvp.domains.interactors;

import com.nandawperdana.androidmvp.api.APICallListener;
import com.nandawperdana.androidmvp.api.APICallManager;
import com.nandawperdana.androidmvp.api.contact.ContactsModel;
import com.nandawperdana.androidmvp.utils.Enums;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandawperdana on 1/18/2017.
 */

public class ContactInteractor implements Interactor {
    APICallListener listener;

    public ContactInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetContacts() {
        final Enums.APIRoute route = Enums.APIRoute.GET_CONTACTS;
        Call<ContactsModel> call = APICallManager.getInstance().contactManager.getContacts();
        call.enqueue(new Callback<ContactsModel>() {
            @Override
            public void onResponse(Call<ContactsModel> call, Response<ContactsModel> response) {
                listener.onAPICallSucceed(route, response.body());
            }

            @Override
            public void onFailure(Call<ContactsModel> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
