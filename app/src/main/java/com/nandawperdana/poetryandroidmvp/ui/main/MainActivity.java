package com.nandawperdana.poetryandroidmvp.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nandawperdana.poetryandroidmvp.R;
import com.nandawperdana.poetryandroidmvp.presentation.presenters.MainPresenter;
import com.nandawperdana.poetryandroidmvp.ui.main.mvp.MainModel;
import com.nandawperdana.poetryandroidmvp.ui.main.mvp.MainPresenterImpl;
import com.nandawperdana.poetryandroidmvp.utils.Enums;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {
    private MainPresenter mPresenter;
    private MainModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        mPresenter.presentState(ViewState.LOAD_GET_AUTHORS);
    }

    private void initLayout() {
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        mPresenter = new MainPresenterImpl(this);
        mModel = new MainModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

    @Override
    public void showState(MainPresenter.MainView.ViewState viewState) {
        switch (viewState) {
            case IDLE:
                showProgress(false);
                break;
            case LOADING:
                showProgress(true);
                break;
            case SHOW_GET_AUTHORS:
                showGetAuthors();
                mPresenter.presentState(ViewState.IDLE);
                break;
            case SHOW_GET_AUTHOR_POETS:
                showGetAuthorPoets();
                mPresenter.presentState(ViewState.IDLE);
                break;
            case ERROR:
                showErrorDialog(mModel.getErrorState());
                break;
        }
    }

    @Override
    public MainModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showProgress(boolean flag) {
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MainActivity.this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String title, String message) {
        new MaterialDialog.Builder(MainActivity.this)
                .title(title)
                .content(message)
                .positiveText(R.string.error_dialog_positive)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .autoDismiss(false)
                .cancelable(false)
                .contentColor(getResources().getColor(R.color.dark))
                .backgroundColorRes(R.color.bone_white)
                .show();
    }

    /**
     * show AuthorPoetsModel to UI
     */
    private void showGetAuthorPoets() {

    }

    /**
     * show AuthorsModel to UI
     */
    private void showGetAuthors() {

    }

    /**
     * show error message
     * @param state
     */
    private void showErrorDialog(Enums.ErrorState state) {
        switch (state) {
            case API:
                showError(getString(R.string.error_message_title_oops), getString(R.string.error_message_api));
                break;
            case NO_INTERNET:
                showError(getString(R.string.error_message_title_oops), getString(R.string.error_message_no_internet));
                break;
        }
    }
}
