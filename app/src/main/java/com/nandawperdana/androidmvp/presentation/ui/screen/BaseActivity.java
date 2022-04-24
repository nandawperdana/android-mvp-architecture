package com.nandawperdana.androidmvp.presentation.ui.screen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.snackbar.Snackbar;
import com.nandawperdana.androidmvp.R;
import com.nandawperdana.androidmvp.presentation.presenters.base.BaseView;

/**
 * Created by nandawperdana.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        this.progressDialog = new ProgressDialog(BaseActivity.this);
        progressDialog.setMessage(getString(R.string.message_loading));
        progressDialog.setCancelable(false);
    }

    @Override
    public void showProgress(boolean flag) {
        if (flag)
            progressDialog.show();
        else progressDialog.dismiss();
    }

    @Override
    public void showToast(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView
                .findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
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
}
