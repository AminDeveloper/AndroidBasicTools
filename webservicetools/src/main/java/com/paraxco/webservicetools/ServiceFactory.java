//package com.paraxco.commontools.WebServiceTools;
//
///**
// * Created by Amin on 5/17/2017.
// */
//
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.paraxco.commontools.WebServiceTools.Interfaces.WebService;
//
//import okhttp3.OkHttpClient;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//
//public class ServiceFactory {
//
//    private static OkHttpClient client;
//
//    /**
//     * Creates a retrofit service from an arbitrary class (clazz)
//     *
//     * @param clazz       Java interface of the retrofit service
//     * @param baseUrl     REST endpoint url
//     * @param resultClass
//     * @return retrofit service with defined endpoint
//     */
//    public static <T> T createRetrofitService(final Class<T> clazz, final String baseUrl, Class resultClass) {
//        return getClient(baseUrl, resultClass).create(clazz);
//    }
//
//    public static Retrofit getClient(final String baseUrl, Class resultClass) {
//        LoggingInterceptor interceptor = new LoggingInterceptor();
//        client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(getJsonConverter(resultClass))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(client)
//                .build();
//        return retrofit;
//    }
//
//    private static GsonConverterFactory getJsonConverter(Class clazz) {
//
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(clazz, new StringAdapter())
//                .create();
//        return GsonConverterFactory.create(gson);
//    }
//
//    public static WebService createWebService(Class resultClass) {
//        return ServiceFactory.createRetrofitService(WebService.class, WebService.SERVICE_BASE_URL, resultClass);
//    }
//
//}