//package ir.mo.amin.hosseini.commontools.WebServiceTools;
//
//import android.app.Dialog;
//import android.content.Context;
//
//import ir.mo.amin.hosseini.commontools.Activities.BaseActivity;
//import ir.mo.amin.hosseini.commontools.R;
//import ir.mo.amin.hosseini.commontools.Utils.Utils;
//
//import io.reactivex.Observer;
//import io.reactivex.disposables.Disposable;
//import retrofit2.HttpException;
//
//
///**
// *
// */
//
//public abstract class CustomObserver<T> implements Observer<T> {
//    BaseActivity baseActivity;
//    private Dialog loadingDialog;
//    private boolean requestDialog = false;
//
//    public CustomObserver(BaseActivity activity) {
//        this.baseActivity = activity;
//    }
//
//    public CustomObserver<T> enableLoadingDialog(Context context) {
//        requestDialog = true;
//        return this;
//    }
//
//    public CustomObserver<T> enableLoadingDialog(Context context, boolean enable) {
//        if (enable)
//            requestDialog = true;
//        return this;
//    }
//
//
//    @Override
//    public void onSubscribe(Disposable d) {
//        showLoading();
//
//    }
//
//    @Override
//    public void onNext(T t) {
//        dismisLoading();
//
//    }
//
//    @Override
//    public void onComplete() {
//        dismisLoading();
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        dismisLoading();
//        e.printStackTrace();
//        if (baseActivity == null)
//            return;
//
//        if (e instanceof HttpException ||
//                e instanceof java.net.UnknownHostException ||
//                e instanceof java.net.SocketTimeoutException)
//            BaseActivity.checkInternetConnection(baseActivity);
//        else {
//            baseActivity.showMessage(R.string.unknown_error);
//        }
//    }
//
//    protected void showLoading() {
//
//        if (!requestDialog)
//            return;
//
//        loadingDialog = Utils.showLoadingDialog(baseActivity);
//        //method 2
//        //        loading = new ProgressDialog(getContext());
////        loading.setCancelable(true);
////        loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
////        loading.setMessage(null);
////        loading.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
////        loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
////
////        loading.show();
//
//    }
//
//
//    protected void dismisLoading() {
//        if (loadingDialog == null)
//            return;
//        if (loadingDialog.isShowing())
//            loadingDialog.dismiss();
//    }
//}
