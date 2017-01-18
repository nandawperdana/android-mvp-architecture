package com.nandawperdana.androidmvp.presentation.presenters;

import com.nandawperdana.androidmvp.presentation.presenters.base.BasePresenter;
import com.nandawperdana.androidmvp.presentation.presenters.base.BaseView;
import com.nandawperdana.androidmvp.presentation.ui.screen.about.mvp.AboutModel;

/**
 * Created by nandawperdana on 4/22/2016.
 */
public interface AboutPresenter extends BasePresenter {
    interface AboutView extends BaseView {
        enum ViewState {
            IDLE, LOADING, SHOW_ABOUT, ERROR
        }

        void showState(ViewState state);

        AboutModel doRetrieveModel();
    }

    void presentState(AboutView.ViewState state);
}
