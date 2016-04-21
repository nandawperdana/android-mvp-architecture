package com.nandawperdana.poetryandroidmvp.ui.main.mvp;

import com.nandawperdana.poetryandroidmvp.presenters.MainPresenter;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public class MainPresenterImpl implements MainPresenter {
    private MainView mView;

    public MainPresenterImpl(MainView mView) {
        this.mView = mView;
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
}
