package com.nandawperdana.androidmvp.presentation.ui.screen.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nandawperdana.androidmvp.R;
import com.nandawperdana.androidmvp.presentation.presenters.MainPresenter;
import com.nandawperdana.androidmvp.presentation.ui.adapter.ContactAdapter;
import com.nandawperdana.androidmvp.presentation.ui.screen.about.AboutActivity;
import com.nandawperdana.androidmvp.presentation.ui.screen.main.mvp.MainModel;
import com.nandawperdana.androidmvp.presentation.ui.screen.main.mvp.MainPresenterImpl;
import com.nandawperdana.androidmvp.utils.Enums;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {
    private MainPresenter mPresenter;
    private MainModel mModel;

    @Bind(R.id.textview_main)
    TextView textView;
    //    @Bind(R.id.swiperefresh_main)
//    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerview_main)
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        mPresenter.presentState(ViewState.LOAD_GET_CONTACTS);
    }

    private void initLayout() {
        ButterKnife.bind(this);
        this.progressDialog = new ProgressDialog(MainActivity.this);

        init();
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.message_loading));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(new ContactAdapter(doRetrieveModel().getListContact(), MainActivity.this));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_about:
                mPresenter.presentState(ViewState.OPEN_ACTIVITY_ABOUT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
            case SHOW_GET_CONTACTS:
                showContacts();
                break;
            case OPEN_ACTIVITY_ABOUT:
                openActivityAbout();
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
        if (flag)
            progressDialog.show();
        else progressDialog.dismiss();
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
     * show ContactsModel to UI
     */
    private void showContacts() {
        // set API response to model
        doRetrieveModel().setListContact();

        // show the data
        recyclerView.getAdapter().notifyDataSetChanged();

        mPresenter.presentState(ViewState.IDLE);
    }

    private void openActivityAbout() {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    /**
     * show error message
     *
     * @param state
     */
    private void showErrorDialog(Enums.ErrorState state) {
        switch (state) {
            case API:
                showError(getString(R.string.error_message_title_oops),
                        getString(R.string.error_message_api));
                break;
            case NO_INTERNET:
                showError(getString(R.string.error_message_title_oops),
                        getString(R.string.error_message_no_internet));
                break;
        }
    }
}
