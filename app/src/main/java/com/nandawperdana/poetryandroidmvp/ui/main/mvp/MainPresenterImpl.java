package com.nandawperdana.poetryandroidmvp.ui.main.mvp;

import com.nandawperdana.poetryandroidmvp.api.APICallListener;
import com.nandawperdana.poetryandroidmvp.api.RootResponseModel;
import com.nandawperdana.poetryandroidmvp.api.author.AuthorPoetsModel;
import com.nandawperdana.poetryandroidmvp.api.author.AuthorsModel;
import com.nandawperdana.poetryandroidmvp.domains.interactors.GetAuthorPoetsInteractor;
import com.nandawperdana.poetryandroidmvp.domains.interactors.GetAuthorsInteractor;
import com.nandawperdana.poetryandroidmvp.presentation.presenters.MainPresenter;
import com.nandawperdana.poetryandroidmvp.utils.Enums;

import java.util.List;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public class MainPresenterImpl implements MainPresenter, APICallListener {
    private MainView mView;
    private GetAuthorsInteractor mGetAuthorsInteractor;
    private GetAuthorPoetsInteractor mGetAuthorPoetsInteractor;

    public MainPresenterImpl(MainView mView) {
        this.mView = mView;
        this.mGetAuthorsInteractor = new GetAuthorsInteractor(this);
        this.mGetAuthorPoetsInteractor = new GetAuthorPoetsInteractor(this);
    }

    @Override
    public void presentState(MainView.ViewState state) {
        switch (state) {
            case IDLE:
                mView.showState(MainView.ViewState.IDLE);
                break;
            case LOADING:
                mView.showState(MainView.ViewState.LOADING);
                break;
            case LOAD_GET_AUTHORS:
                presentState(MainView.ViewState.LOADING);
                mGetAuthorsInteractor.callAPIGetAuthor();
                break;
            case SHOW_GET_AUTHORS:
                mView.showState(MainView.ViewState.SHOW_GET_AUTHORS);
                break;
            case LOAD_GET_AUTHOR_POETS:
                presentState(MainView.ViewState.LOADING);
                mGetAuthorPoetsInteractor.callAPIGetAuthorPoets(mView.doRetrieveModel().getAuthorName());
                break;
            case SHOW_GET_AUTHOR_POETS:
                mView.showState(MainView.ViewState.SHOW_GET_AUTHOR_POETS);
                break;
            case ERROR:
                mView.showState(MainView.ViewState.ERROR);
                break;
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, RootResponseModel responseModel) {
//        presentState(MainView.ViewState.IDLE);
        switch (route) {
            case GET_AUTHORS:
                mView.doRetrieveModel().authorsDomain.setModel((AuthorsModel) responseModel);
                presentState(MainView.ViewState.SHOW_GET_AUTHORS);
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
//        presentState(MainView.ViewState.IDLE);
        switch (route) {
            case GET_AUTHOR_POETS:
//                List<AuthorPoetsModel> subResponseModels = (List<AuthorPoetsModel>) (List<?>) responseModels;
                List<AuthorPoetsModel> subResponseModels = (List<AuthorPoetsModel>) (AuthorPoetsModel) responseModels;
                mView.doRetrieveModel().authorPoetsDomain.setModels(subResponseModels);

                presentState(MainView.ViewState.SHOW_GET_AUTHOR_POETS);
                break;
        }
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        presentState(MainView.ViewState.IDLE);
        mView.doRetrieveModel().setErrorState(Enums.ErrorState.API);
        switch (route) {
            case GET_AUTHORS:
                presentState(MainView.ViewState.ERROR);
                break;
            case GET_AUTHOR_POETS:
                presentState(MainView.ViewState.ERROR);
                break;
        }
    }
}
