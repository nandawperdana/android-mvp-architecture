package com.nandawperdana.poetryandroidmvp.ui.about;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nandawperdana.poetryandroidmvp.R;
import com.nandawperdana.poetryandroidmvp.presentation.presenters.AboutPresenter;
import com.nandawperdana.poetryandroidmvp.ui.about.mvp.AboutModel;
import com.nandawperdana.poetryandroidmvp.ui.about.mvp.AboutPresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements AboutPresenter.AboutView {
    private AboutPresenter mPresenter;
    private AboutModel mModel;

    @Bind(R.id.textview_about)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initLayout();
        mPresenter.presentState(ViewState.SHOW_ABOUT);
    }

    private void initLayout() {
        ButterKnife.bind(AboutActivity.this);

        init();
    }

    private void init() {
        this.mPresenter = new AboutPresenterImpl(this);
        this.mModel = new AboutModel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showState(ViewState state) {
        switch (state) {
            case IDLE:
                showProgress(false);
                break;
            case LOADING:
                showProgress(true);
                break;
            case SHOW_ABOUT:
                showAbout();
                break;
            case ERROR:
                showError(getString(R.string.error_message_title_oops), getString(R.string.error_message_api));
                break;
        }
    }

    @Override
    public AboutModel doRetrieveModel() {
        return this.mModel;
    }

    @Override
    public void showProgress(boolean flag) {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(AboutActivity.this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String title, String message) {
        new MaterialDialog.Builder(AboutActivity.this)
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

    private void showAbout() {
    }
}
