package com.paraxco.commontools.WebServiceTools;

import android.webkit.MimeTypeMap;

import com.paraxco.commontools.Utils.Utils;
import com.paraxco.commontools.WebServiceTools.Interfaces.WebService;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 *
 */

public class ServiceHelper {
    private static String KEY = "vrs@#R#$_0^!*F@#DS#";

    public class StringResult extends ServiceResault<String> {
    }

    public static void login(String mobile, String password, Observer<? super StringResult> subscriber) {
        WebService service = ServiceFactory.createWebService(String.class);
        service.getUser(mobile, password, "parent", "login", KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

//    public class PointResult extends ServiceResault<MapPoint[]> {
//    }
//
//    public static void getPoints(long userID, long currentStudent, long lastTime, Observer<? super PointResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(MapPoint[].class);
//        service.getPoints(userID, currentStudent, lastTime, "points", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public class DriverResult extends ServiceResault<DriverProfile> {
//    }
//
//    public static void getDriverProfile(long userID, long currentStudent, Observer<? super DriverResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(DriverProfile.class);
//        service.getDriverProfile(userID, currentStudent, "driver","parent", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public class AboutUsResult extends ServiceResault<AboutInfo> {
//    }
//
//    public static void getAboutUs(Observer<? super AboutUsResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(AboutInfo.class);
//        service.getAboutUs("about", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public class GetMessagesResult extends ServiceResault<List<Message>> {
//    }
//
//    public static void getMessages(long userID, Observer<? super GetMessagesResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(List.class);
//        service.getMessages(userID, "list", "messages", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public class GetNewsResult extends ServiceResault<List<News>> {
//    }
//
//    public static void getNews(Observer<? super GetNewsResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(List.class);
//        service.getNews("list", "parent", "news", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }


//    public static void sendMessages(long userID, int type, String text, String title, Observer<? super StringResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(String.class);
//        service.sendMessages(userID, "new", type, text, title, "messages", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public static void deleteMessages(long userID, long messageID, Observer<? super StringResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(String.class);
//        service.deleteMessage(userID, messageID, "remove", "messages", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public static void sendToken(long userID, String token, Observer<? super StringResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(String.class);
//        service.sendToken(userID, token, "parent", "token", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public static void changePassword(long userID, String password, Observer<? super StringResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(String.class);
//        service.changePassword(userID, password, "password", "parent", "profile", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

//    public class ParentProfileResult extends ServiceResault<ParentProfile> {
//    }
//
//    public static void getParentProfile(long userID, Observer<? super ParentProfileResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(ParentProfile.class);
//        service.getParentProfile(userID, "client", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public class GalleryResult extends ServiceResault<List<GalleryPic>> {
//    }

//    public static void getGalleryImages(long userID, Observer<? super GalleryResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(List.class);
//        service.getGaleryImages(userID, "gallery", "trip", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

//    public static void getCodeForChangePassword(String mobile, Observer<? super StringResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(String.class);
//        service.getCodeForChangePassword(mobile, "1", "forgotpassword", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public static void sendCodeForChangePassword(String mobile, String code, Observer<? super StringResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(String.class);
//        service.sendCodeForChangePassword(mobile, code, "2", "forgotpassword", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
////    public static void updateParentProfile(long userID, String name, String family, String telegram, String parent_image, List<Child> students, Observer<? super StringResult> subscriber) {
////        WebService service = ServiceFactory.createWebService(String.class);
////        service.updateParentProfile(userID, name, family, telegram, parent_image, toJsonList(students), "update", "profile", KEY)
////                .subscribeOn(Schedulers.newThread())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
////    }
//
//    public static void uploadParentAvatar(long userID, File image, Observer<? super StringResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(String.class);
//        service.uploadAvatrImage(getRequest(String.valueOf(userID)),
//                getRequest("image"), getRequest("parent"), getRequest("profile"), getRequest(KEY), getFilePart(image, "image"))
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    private static RequestBody getRequest(String content) {
//        RequestBody requestBode = RequestBody.create(MultipartBody.FORM, content);
//
//        return requestBode;
//    }
//
//    public static void uploadStudentAvatar(long childId, File image, Observer<? super StringResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(String.class);
//        service.uploadAvatrImage(getRequest(String.valueOf(childId)),
//                getRequest("image"), getRequest("student"), getRequest("profile"), getRequest(KEY), getFilePart(image, "image"))
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }
//
//    public static void getSharedURL(long userId, long tripId, Observer<? super StringResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(String.class);
//        service.getSharedURL(userId, tripId, "share", "trip", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

//    public class PackageResult extends ServiceResault<List<PackageData>> {
//    }

//    public static void getPackages(long userId, Observer<? super PackageResult> subscriber) {
//        WebService service = ServiceFactory.createWebService(List.class);
//        service.getPackages(userId, "list", "package", KEY)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

    public static void getPackageInfo(long userID, long packageId, CustomObserver<StringResult> subscriber) {
        WebService service = ServiceFactory.createWebService(String.class);
        service.getPackageInfo(userID, packageId, "payment", KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    //tools!

    private static MultipartBody.Part getFilePart(File file, String name) {
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
// MultipartBody.Part is used to send also the actual file name
        String mineType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(Utils.getExtention(file));
        MultipartBody.Part body =
                MultipartBody.Part.createFormData(name, file.getName(), requestFile);
        return body;
    }

//    private static String toJsonList(List items) {
//        GsonBuilder builder = new GsonBuilder();
//
//        builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
//        builder.addSerializationExclusionStrategy(new ExclusionStrategy() {
//            @Override
//            public boolean shouldSkipField(FieldAttributes f) {
//                return false;
//            }
//
//            @Override
//            public boolean shouldSkipClass(Class<?> clazz) {
//                return (clazz == Model.class);
//            }
//        });
//        Gson sExposeGson = builder.create();
//        return sExposeGson.toJson(items);
//    }
}
