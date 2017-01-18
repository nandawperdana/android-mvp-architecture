package com.nandawperdana.androidmvp.presentation.presenters;

import com.nandawperdana.androidmvp.presentation.presenters.base.BasePresenter;
import com.nandawperdana.androidmvp.presentation.presenters.base.BaseView;
import com.nandawperdana.androidmvp.presentation.ui.screen.main.mvp.MainModel;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public interface MainPresenter extends BasePresenter {

    interface MainView extends BaseView {
        /**
         * This enum is used for determine the current state of this screen
         */
        enum ViewState {
            IDLE, LOADING,
            LOAD_GET_CONTACTS, SHOW_GET_CONTACTS,
            OPEN_ACTIVITY_ABOUT, ERROR
        }

        /**
         * This method is to show the current state of this screen
         *
         * @param viewState
         */
        void showState(ViewState viewState);

        /**
         * This function return the model that was belong to this screen
         *
         * @return
         */
        MainModel doRetrieveModel();
    }

    /**
     * This method is used for presenting the current state of this screen
     *
     * @param state
     */
    void presentState(MainView.ViewState state);
}
