package com.nandawperdana.poetryandroidmvp.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nandawperdana.poetryandroidmvp.R;
import com.nandawperdana.poetryandroidmvp.presenters.MainPresenter;
import com.nandawperdana.poetryandroidmvp.ui.main.mvp.MainModel;
import com.nandawperdana.poetryandroidmvp.ui.main.mvp.MainPresenterImpl;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {
    private MainPresenter mPresenter;
    private MainModel mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
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
            case ERROR:
                showError("");
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
    public void showError(String message) {
        showToast(message);
    }
}
