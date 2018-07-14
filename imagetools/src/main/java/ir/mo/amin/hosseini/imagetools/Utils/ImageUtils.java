package ir.mo.amin.hosseini.imagetools.Utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;


/**
 * Created by Amin on 11/6/2017.
 */

public class ImageUtils {
//    public static void loadCircleWithGlide(final Context context, String url, final ImageView imageView) {
//        Glide.with(context).load(url).asBitmap().into(new BitmapImageViewTarget(imageView) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                imageView.setImageDrawable(circularBitmapDrawable);
//            }
//
//            @Override
//            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(context.getResources(), getdefaultProfile(context));
//                circularBitmapDrawable.setCircular(true);
//                imageView.setImageDrawable(circularBitmapDrawable);
//            }
//        });
//    }

    private static Bitmap getdefaultProfile(Context context) {
        return null;
//        return getBitmapFromVectorDrawable(context,  R.drawable.default_profile);
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);

        return getBitmap(drawable);
    }

    public static Bitmap getBitmap(Drawable drawable) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

//    public static void loadWithGlide(final Context context, String url, final ImageView imageView) {
//        Glide.with(context).load(url)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
////                .placeholder(R.drawable.logo)
//                .into(imageView);
//    }
//
//    public static void loadWithGlide(final Context context, String url, int width, int height, final ImageView imageView) {
//        Glide.with(context).load(url)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
////                .placeholder(R.drawable.logo)
//                .override(width, height)
//                .into(imageView);
//    }

    public static Bitmap takeScreenShot( Activity activity)  {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        Rect frame =new Rect();
        activity. getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();

        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

}
