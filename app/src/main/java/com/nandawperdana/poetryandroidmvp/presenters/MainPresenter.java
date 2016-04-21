package com.nandawperdana.poetryandroidmvp.presenters;

import com.nandawperdana.poetryandroidmvp.presenters.base.BasePresenter;
import com.nandawperdana.poetryandroidmvp.ui.BaseView;
import com.nandawperdana.poetryandroidmvp.ui.main.mvp.MainModel;

/**
 * Created by nandawperdana on 4/21/2016.
 */
public interface MainPresenter extends BasePresenter {

    interface MainView extends BaseView {
        /**
         * This enum is used for determine the current state of this screen
         */
        enum ViewState {
            IDLE, LOADING, ERROR
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
     * @param state
     */
    void presentState(MainView.ViewState state);
}
