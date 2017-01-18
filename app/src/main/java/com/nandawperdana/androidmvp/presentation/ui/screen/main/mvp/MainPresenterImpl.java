package com.nandawperdana.androidmvp.presentation.ui.screen.main.mvp;

import com.nandawperdana.androidmvp.api.APICallListener;
import com.nandawperdana.androidmvp.api.RootResponseModel;
import com.nandawperdana.androidmvp.api.contact.ContactsModel;
import com.nandawperdana.androidmvp.domains.interactors.ContactInteractor;
import com.nandawperdana.androidmvp.presentation.presenters.MainPresenter;
import com.nandawperdana.androidmvp.utils.Enums;

import java.util.List;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public class MainPresenterImpl implements MainPresenter, APICallListener {
    private MainView mView;
    private ContactInteractor mContactInteractor;

    public MainPresenterImpl(MainView mView) {
        this.mView = mView;
        this.mContactInteractor = new ContactInteractor(this);
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
            case LOAD_GET_CONTACTS:
                presentState(MainView.ViewState.LOADING);
                mContactInteractor.callAPIGetContacts();
                break;
            case SHOW_GET_CONTACTS:
                mView.showState(MainView.ViewState.SHOW_GET_CONTACTS);
                break;
            case OPEN_ACTIVITY_ABOUT:
                mView.showState(MainView.ViewState.OPEN_ACTIVITY_ABOUT);
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
            case GET_CONTACTS:
                mView.doRetrieveModel().contactsDomain.setModel((ContactsModel) responseModel);
                presentState(MainView.ViewState.SHOW_GET_CONTACTS);
                break;
        }
    }

    @Override
    public void onAPICallSucceed(Enums.APIRoute route, List<RootResponseModel> responseModels) {
    }

    @Override
    public void onAPICallFailed(Enums.APIRoute route, Throwable throwable) {
        presentState(MainView.ViewState.IDLE);
        mView.doRetrieveModel().setErrorState(Enums.ErrorState.API);
        presentState(MainView.ViewState.ERROR);
    }
}
