package com.nandawperdana.poetryandroidmvp.domains.interactors;

import com.nandawperdana.poetryandroidmvp.api.APICallListener;
import com.nandawperdana.poetryandroidmvp.api.APICallManager;
import com.nandawperdana.poetryandroidmvp.api.author.AuthorsModel;
import com.nandawperdana.poetryandroidmvp.utils.Enums;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public class GetAuthorsInteractor implements Interactor {
    APICallListener listener;

    public GetAuthorsInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAuthor() {
        final Enums.APIRoute route = Enums.APIRoute.GET_AUTHORS;
        Call<AuthorsModel> call = APICallManager.getInstance().authorManager.getAuthor();
        call.enqueue(new Callback<AuthorsModel>() {
            @Override
            public void onResponse(Call<AuthorsModel> call, Response<AuthorsModel> response) {
                listener.onAPICallSucceed(route, response.body());
            }

            @Override
            public void onFailure(Call<AuthorsModel> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
