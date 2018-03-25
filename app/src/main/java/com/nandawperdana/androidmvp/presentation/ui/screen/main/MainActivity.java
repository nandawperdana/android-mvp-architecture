package com.nandawperdana.androidmvp.presentation.ui.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nandawperdana.androidmvp.R;
import com.nandawperdana.androidmvp.presentation.presenters.MainPresenter;
import com.nandawperdana.androidmvp.presentation.ui.adapter.PeopleAdapter;
import com.nandawperdana.androidmvp.presentation.ui.screen.BaseActivity;
import com.nandawperdana.androidmvp.presentation.ui.screen.about.AboutActivity;
import com.nandawperdana.androidmvp.presentation.ui.screen.main.mvp.MainModel;
import com.nandawperdana.androidmvp.presentation.ui.screen.main.mvp.MainPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainPresenter.MainView {
    private MainPresenter presenter;
    private MainModel model;

    @BindView(R.id.textview_main)
    TextView textView;
    @BindView(R.id.recyclerview_main)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        presenter.presentState(ViewState.LOAD_PEOPLE);
    }

    private void init() {
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);
        model = new MainModel(this);

        initLayout();
    }

    private void initLayout() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new PeopleAdapter(doRetrieveModel().getListPeople(), MainActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_about:
                presenter.presentState(ViewState.OPEN_ABOUT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress(boolean flag) {
        if (flag) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
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
            case SHOW_PEOPLE:
                showPeople();
                break;
            case OPEN_ABOUT:
                openActivityAbout();
                break;
            case ERROR:
                showToast(doRetrieveModel().getErrorMessage());
                break;
        }
    }

    @Override
    public MainModel doRetrieveModel() {
        return this.model;
    }

    /**
     * show PeopleResponse to UI
     */
    private void showPeople() {
        // show the data
        recyclerView.getAdapter().notifyDataSetChanged();

        presenter.presentState(ViewState.IDLE);
    }

    private void openActivityAbout() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }
}
