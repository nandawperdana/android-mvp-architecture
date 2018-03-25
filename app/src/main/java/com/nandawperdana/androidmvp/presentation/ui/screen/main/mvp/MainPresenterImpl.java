package com.nandawperdana.androidmvp.presentation.ui.screen.main.mvp;

import com.nandawperdana.androidmvp.api.APICallListener;
import com.nandawperdana.androidmvp.api.BaseResponse;
import com.nandawperdana.androidmvp.api.people.PeopleResponse;
import com.nandawperdana.androidmvp.domains.interactors.PeopleInteractor;
import com.nandawperdana.androidmvp.presentation.presenters.MainPresenter;
import com.nandawperdana.androidmvp.utils.Enums;

import java.util.List;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public class MainPresenterImpl implements MainPresenter, APICallListener {
    private MainView view;
    private PeopleInteractor peopleInteractor;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        this.peopleInteractor = new PeopleInteractor(this);
    }

    @Override
    public void presentState(MainView.ViewState state) {
        switch (state) {
            case IDLE:
                view.showState(MainView.ViewState.IDLE);
                break;
            case LOADING:
                view.showState(MainView.ViewState.LOADING);
                break;
            case LOAD_PEOPLE:
                presentState(MainView.ViewState.LOADING);
                peopleInteractor.callAPIGetContacts();
                break;
            case SHOW_PEOPLE:
                // set API response to model
                view.doRetrieveModel().setListPeople();
                view.showState(MainView.ViewState.SHOW_PEOPLE);
                break;
            case OPEN_ABOUT:
                view.showState(MainView.ViewState.OPEN_ABOUT);
                break;
            case ERROR:
                view.showState(MainView.ViewState.ERROR);
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
        view.doRetrieveModel().setErrorMessage(message);
        presentState(MainView.ViewState.IDLE);
        presentState(MainView.ViewState.ERROR);
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, BaseResponse responseModel) {
        switch (route) {
            case GET_PEOPLE:
                view.doRetrieveModel().peopleDomain.setModel((PeopleResponse) responseModel);
                presentState(MainView.ViewState.SHOW_PEOPLE);
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<BaseResponse> responseModels) {
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        onError(throwable.getMessage());
    }
}
