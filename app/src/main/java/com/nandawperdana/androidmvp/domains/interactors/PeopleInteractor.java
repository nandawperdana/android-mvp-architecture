package com.nandawperdana.androidmvp.domains.interactors;

import com.nandawperdana.androidmvp.api.APICallListener;
import com.nandawperdana.androidmvp.api.APICallManager;
import com.nandawperdana.androidmvp.api.people.PeopleResponse;
import com.nandawperdana.androidmvp.utils.Enums;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandawperdana on 1/18/2017.
 */

public class PeopleInteractor implements Interactor {
    APICallListener listener;

    public PeopleInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetContacts() {
        final Enums.APIRoute route = Enums.APIRoute.GET_PEOPLE;
        Call<PeopleResponse> call = APICallManager.getInstance().peopleManager.getContacts();
        call.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                listener.onAPICallSucceed(route, response.body());
            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
