package com.paraxco.basictools.Activities;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.paraxco.basictools.BroadCastReceiver.NetworkChangeReceiver;
import com.paraxco.basictools.R;
import com.paraxco.basictools.Utils.Permision.PermisionUtils;
import com.paraxco.basictools.Utils.Utils;

import java.util.Locale;

/**
 *
 */

public abstract class BaseActivity extends AppCompatActivity implements NetworkChangeReceiver.NetworkListener {
    private boolean showMessagesFromTop = false;

    private String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.INTERNET,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    private Snackbar networkSnakeBar;
    private TSnackbar topNetworkSnakeBar;
    PermisionUtils permisionUtils;


    public static ContextWrapper wrap(Context context, String language) {
        Configuration config = context.getResources().getConfiguration();
        Locale sysLocale = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = getSystemLocale(config);
        } else {
            sysLocale = getSystemLocaleLegacy(config);
        }
        if (!language.equals("") && !sysLocale.getLanguage().equals(language)) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setSystemLocale(config, locale);
            } else {
                setSystemLocaleLegacy(config, locale);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(config);
            } else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }
        }
        return new ContextWrapper(context);
    }

    public static Locale getSystemLocaleLegacy(Configuration config) {
        return config.locale;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Locale getSystemLocale(Configuration config) {
        return config.getLocales().get(0);
    }

    public static void setSystemLocaleLegacy(Configuration config, Locale locale) {
        config.locale = locale;
    }

    public static void setSystemLocale(Configuration config, Locale locale) {
        config.setLocale(locale);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
//        super.attachBaseContext(wrap(newBase, "fa"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        NetworkChangeReceiver.registerObserver(getApplicationContext(), this);
        chageConfiguration();
    }

    @Override
    protected void onStop() {
        super.onStop();
        NetworkChangeReceiver.unRegisterObserver(getApplicationContext(), this);

    }

    /**
     * changes local to persian "fa"
     */
    private void chageConfiguration() {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(new Locale("fa"));
        createConfigurationContext(configuration);
    }



    public boolean checkInternetConnection() {
        return checkInternetConnection(this);
    }

    public static boolean checkInternetConnection(BaseActivity activity) {
        if (!Utils.isNetworkAvailable(activity)) {
            activity.showNetworkSnakeBar();
            return false;
        } else {
            activity.dismisNetworkSnakeBar();
            return true;
        }
    }

    public static boolean checkInternetConnectionShowTop(BaseActivity activity) {
        if (!Utils.isNetworkAvailable(activity)) {
            activity.showTopNetworkSnakeBar();
            return false;
        } else {
            activity.dismisTopNetworkSnakeBar();
            return true;
        }
    }


    @Override
    public void onNetworkStateChange(boolean connected) {
        if (!connected)
            showNetworkSnakeBar();
        else
            dismisNetworkSnakeBar();
    }


    private void dismisNetworkSnakeBar() {
        if (networkSnakeBar != null)
            networkSnakeBar.dismiss();
        dismisTopNetworkSnakeBar();
    }

    private void dismisTopNetworkSnakeBar() {
        if (topNetworkSnakeBar != null)
            topNetworkSnakeBar.dismiss();
    }

    private void showNetworkSnakeBar() {
        if (showMessagesFromTop) {
            showTopNetworkSnakeBar();
        } else {
            showBottomNetworkSnakeBar();
        }
    }

    private void showBottomNetworkSnakeBar() {

        View coordinatorLayout=null;
//        coordinatorLayout = findViewById(R.id.base_snak_bar);
        if (coordinatorLayout == null)
            coordinatorLayout = getWindow().getDecorView().getRootView();

        networkSnakeBar = Snackbar
                .make(coordinatorLayout, getString(R.string.no_internet), Snackbar.LENGTH_SHORT)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkInternetConnection();
                    }
                });

// Changing message text color
        networkSnakeBar.setActionTextColor(Color.RED);

// Changing action button text color
        View sbView = networkSnakeBar.getView();
        sbView.setAlpha(0.6f);
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        networkSnakeBar.show();
    }


    private void showTopNetworkSnakeBar() {

        View coordinatorLayout=null;
//        coordinatorLayout = findViewById(R.id.base_snak_bar);
        if (coordinatorLayout == null)
            coordinatorLayout = getWindow().getDecorView().getRootView();

        topNetworkSnakeBar = TSnackbar
                .make(coordinatorLayout, getString(R.string.no_internet), Snackbar.LENGTH_SHORT)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkInternetConnection();
                    }
                });

// Changing message text color
        topNetworkSnakeBar.setActionTextColor(Color.RED);

// Changing action button text color
        View sbView = topNetworkSnakeBar.getView();
        sbView.setAlpha(0.6f);
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        topNetworkSnakeBar.show();
    }

    void showMessagesFromTop() {
        showMessagesFromTop = true;
    }

    public void showMessage(String message) {
        if (showMessagesFromTop)
            showTopMessage(message);
        else
            showBottomMessage(message);
    }

    public void showTopMessage(String message) {
        View coordinatorLayout=null;
//        coordinatorLayout = findViewById(R.id.base_snak_bar);
        if (coordinatorLayout == null)
            coordinatorLayout = getWindow().getDecorView().getRootView();

        TSnackbar snackbar = TSnackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setAlpha(0.6f);
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.gray2));
        snackbar.show();
    }

    private void showBottomMessage(String message) {
        View coordinatorLayout=null;
//        coordinatorLayout = findViewById(R.id.base_snak_bar);
        if (coordinatorLayout == null)
            coordinatorLayout = getWindow().getDecorView().getRootView();
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();

        sbView.setAlpha(0.6f);

        snackbar.show();
    }

    public void showMessage(int res) {
        showMessage(this.getString(res));
    }

//    LoadingDialogHelper dialogHelper=new LoadingDialogHelper();

    @Deprecated
    protected void changeLocale() {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        configuration.setLocale(new Locale("fa"));
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public OnActivityResultListener onActivityResultListener;

    public void setOnActivityResultListener(OnActivityResultListener onActivityResultListener) {
        this.onActivityResultListener = onActivityResultListener;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (onActivityResultListener != null)
            onActivityResultListener.onActivityResult(requestCode, resultCode, data);
    }

    public interface OnActivityResultListener {
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    //permisions

    /**
     * request all permisions
     */
    public synchronized void requestPermisions(PermisionUtils.PermisionGrantListener permisionGrantListener) {
        requestPermisions(permissions, permisionGrantListener);
    }

    public void requestPermisions(final String[] permissions, final PermisionUtils.PermisionGrantListener permisionGrantListener) {
        permisionUtils.requestPermisions(permissions, permisionGrantListener);
    }

    public void requestPermisions(final String permission, final PermisionUtils.PermisionGrantListener permisionGrantListener) {

        String[] permissions = new String[]{permission};
        permisionUtils.requestPermisions(permissions, permisionGrantListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permisionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}

