package com.nandawperdana.androidmvp.presentation.ui.screen.about;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nandawperdana.androidmvp.R;
import com.nandawperdana.androidmvp.presentation.presenters.AboutPresenter;
import com.nandawperdana.androidmvp.presentation.ui.screen.about.mvp.AboutModel;
import com.nandawperdana.androidmvp.presentation.ui.screen.about.mvp.AboutPresenterImpl;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class AboutActivity extends AppCompatActivity implements AboutPresenter.AboutView {
    private AboutPresenter mPresenter;
    private AboutModel mModel;

    @BindView(R.id.textview_about)
    TextView textView;
    @BindView(R.id.textview_about_version)
    TextView textViewVersion;
    @BindView(R.id.imageview_about)
    CircleImageView imageView;
    @BindView(R.id.layout_about_github)
    LinearLayout lsyoutGithub;

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

        new MaterialDialog(this, MaterialDialog.getDEFAULT_BEHAVIOR())
                .title(null, title)
                .message(null, message, null)
                .positiveButton(R.string.error_dialog_positive, null, materialDialog -> {
                    materialDialog.dismiss();
                    return null;
                })
                .noAutoDismiss()
                .cancelable(false)
                .show();
    }

    private void showAbout() {
        String url = "https://graph.facebook.com/1399656618/picture?type=large";
        Picasso.get()
                .load(url)
                .error(R.drawable.ic_blank_avatar)
                .placeholder(R.drawable.ic_blank_avatar)
                .into(imageView);

        PackageInfo pInfo;
        String version = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        textViewVersion.setText("Version: " + version);
    }

    @OnClick(R.id.layout_about_github)
    public void onClickGithub() {
        String url = "http://github.com/nandawperdana/android-mvp-architecture";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
