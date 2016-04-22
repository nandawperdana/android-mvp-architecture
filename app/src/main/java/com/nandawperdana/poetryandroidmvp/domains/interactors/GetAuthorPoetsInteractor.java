package com.nandawperdana.poetryandroidmvp.domains.interactors;

import com.nandawperdana.poetryandroidmvp.api.APICallListener;
import com.nandawperdana.poetryandroidmvp.api.APICallManager;
import com.nandawperdana.poetryandroidmvp.api.RootResponseModel;
import com.nandawperdana.poetryandroidmvp.api.author.AuthorPoetsModel;
import com.nandawperdana.poetryandroidmvp.utils.Enums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nandawperdana on 4/22/2016.
 */
public class GetAuthorPoetsInteractor implements Interactor {
    APICallListener listener;

    public GetAuthorPoetsInteractor(APICallListener listener) {
        this.listener = listener;
    }

    public void callAPIGetAuthorPoets(String authorName) {
        final Enums.APIRoute route = Enums.APIRoute.GET_AUTHOR_POETS;
        Call<List<AuthorPoetsModel>> call = APICallManager.getInstance().authorManager.getAuthorPoets(authorName);
        call.enqueue(new Callback<List<AuthorPoetsModel>>() {
            @Override
            public void onResponse(Call<List<AuthorPoetsModel>> call, Response<List<AuthorPoetsModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<AuthorPoetsModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }

    public void callAPIGetAuthorPoetsAbs(String authorName) {
        final Enums.APIRoute route = Enums.APIRoute.GET_AUTHOR_POETS;
        Call<List<AuthorPoetsModel>> call = APICallManager.getInstance().authorManager.getAuthorPoetsAbs(authorName);
        call.enqueue(new Callback<List<AuthorPoetsModel>>() {
            @Override
            public void onResponse(Call<List<AuthorPoetsModel>> call, Response<List<AuthorPoetsModel>> response) {
                listener.onAPICallSucceed(route, new ArrayList<RootResponseModel>(response.body()));
            }

            @Override
            public void onFailure(Call<List<AuthorPoetsModel>> call, Throwable t) {
                listener.onAPICallFailed(route, t);
            }
        });
    }
}
