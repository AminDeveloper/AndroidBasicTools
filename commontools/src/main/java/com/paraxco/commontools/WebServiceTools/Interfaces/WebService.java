package com.paraxco.commontools.WebServiceTools.Interfaces;



import com.paraxco.commontools.WebServiceTools.ServiceHelper;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 *
 */
public interface WebService {
    String SERVICE_BASE_URL = "http://www.varasservice.ir/";
    String REST_OF_URL = "app/service/index.php";

    @FormUrlEncoded
    @POST(REST_OF_URL)
    Observable<ServiceHelper.StringResult> getUser(
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("type") String type,
            @Field("action") String action,
            @Field("key") String key);

//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.PointResult> getPoints(
//            @Field("userid") long userID,
//            @Field("studentid") long currentStudent,
//            @Field("time") long lastTime,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.DriverResult> getDriverProfile(
//            @Field("userid") long userID,
//            @Field("studentid") long currentStudent,
//            @Field("action") String action,
//            @Field("type") String driver,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.AboutUsResult> getAboutUs(
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.GetMessagesResult> getMessages(
//            @Field("userid") long userID,
//            @Field("step") String step,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.GetNewsResult> getNews(
//            @Field("step") String step,
//            @Field("type") String type,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.StringResult> sendMessages(
//            @Field("userid") long userID,
//            @Field("step") String step,
//            @Field("type") int type,
//            @Field("message") String text,
//            @Field("title") String title,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.StringResult> deleteMessage(
//            @Field("userid") long userID,
//            @Field("id") long messageID,
//            @Field("step") String step,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.StringResult> sendToken(
//            @Field("userid") long userID,
//            @Field("token") String token,
//            @Field("type") String type,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.StringResult> changePassword(
//            @Field("userid") long userID,
//            @Field("password") String password,
//            @Field("step") String step,
//            @Field("type") String type,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.ParentProfileResult> getParentProfile(
//            @Field("userid") long userID,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.GalleryResult> getGaleryImages(
//            @Field("userid") long userID,
//            @Field("step") String step,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.StringResult> getCodeForChangePassword(
//            @Field("mobile") String mobile,
//            @Field("step") String step,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.StringResult> sendCodeForChangePassword(
//            @Field("mobile") String mobile,
//            @Field("code") String code,
//            @Field("step") String step,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.StringResult> updateParentProfile(
//            @Field("userid") long userID,
//            @Field("name") String name,
//            @Field("family") String family,
//            @Field("telegram") String telegram,
//            @Field("parent_image") String parent_image,
//            @Field("student") String students,
//            @Field("step") String step,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @Multipart
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.StringResult> uploadAvatrImage(
//            @Part("userid") RequestBody userID,
//            @Part("step") RequestBody step,
//            @Part("type") RequestBody type,
//            @Part("action") RequestBody action,
//            @Part("key") RequestBody key,
//            @Part() MultipartBody.Part image);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.StringResult> getSharedURL(
//            @Field("userid") long mobile,
//            @Field("tripid") long code,
//            @Field("step") String share,
//            @Field("action") String action,
//            @Field("key") String key);
//
//    @FormUrlEncoded
//    @POST(REST_OF_URL)
//    Observable<ServiceHelper.PackageResult> getPackages(
//            @Field("userid") long userId,
//            @Field("step") String step,
//            @Field("action") String action,
//            @Field("key") String key);

    @FormUrlEncoded
    @POST(REST_OF_URL)
    Observable<ServiceHelper.StringResult> getPackageInfo(
            @Field("userid") long userId,
            @Field("packageid") long packageId,
            @Field("action") String action,
            @Field("key") String key);

//    @POST(REST_OF_URL)
//    @Multipart
//    Observable<ServiceHelper.StringResult> UploadImage(@Part("userid") RequestBody requestBody,
//                                                       @Part("action") RequestBody requestBody2,
//                                                       @Part("key") RequestBody requestBody3,
//                                                       @Part("step") RequestBody requestBody4,
//                                                       @Part("tripid") RequestBody requestBody5,
//                                                       @Part MultipartBody.Part part);

}