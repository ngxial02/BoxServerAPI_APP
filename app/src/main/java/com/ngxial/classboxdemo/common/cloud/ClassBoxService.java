package com.ngxial.classboxdemo.common.cloud;

import com.google.gson.Gson;
import com.ngxial.classboxdemo.common.cloud.api.ServiceMetadata;
import com.ngxial.classboxdemo.common.cloud.pojo.Common;
import com.ngxial.classboxdemo.common.cloud.pojo.CreateBoxGroup;
import com.ngxial.classboxdemo.common.cloud.pojo.CreateChannel;
import com.ngxial.classboxdemo.common.cloud.pojo.CreateChannelGroup;
import com.ngxial.classboxdemo.common.cloud.pojo.CreateProgram;
import com.ngxial.classboxdemo.common.cloud.pojo.GetChannel;
import com.ngxial.classboxdemo.common.cloud.pojo.Login;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ClassBoxService {
    private static ClassBoxService instance;
    private ServiceConfig config;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private Gson gson;

    public static synchronized ClassBoxService getInstance() {
        if (instance == null) {
            instance = new ClassBoxService();
        }
        return instance;
    }

    public ClassBoxService() {
        config = ServiceConfig.getInstance();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(config.getCloudUri())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        gson = new Gson();
    }

    public void updateConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl(config.getCloudUri())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public Observable<Login> login(String account, String password) {
        return retrofit.create(ServiceMetadata.class).login(account, password).subscribeOn(Schedulers.io());
    }

    public Observable<CreateBoxGroup> createBoxGroup(String accessToken, String name, String description, String type) {
        return retrofit.create(ServiceMetadata.class).createBoxGroup(accessToken, name, description, type).subscribeOn(Schedulers.io());
    }

    public Observable<CreateChannelGroup> createChannelGroup(String accessToken, String name, String description, String type) {
        return retrofit.create(ServiceMetadata.class).createChannelGroup(accessToken, name, description, type).subscribeOn(Schedulers.io());
    }

    public Observable<CreateChannel> createChannel(
            String accessToken, String rtsp_url, String streaming_key, String name, String description, String remark, String vender_id) {
        return retrofit.create(ServiceMetadata.class).createChannel(
                accessToken, rtsp_url, streaming_key, name, description, remark, vender_id).subscribeOn(Schedulers.io());
    }

    public Observable<GetChannel> getChannel(
            String accessToken, String rtsp_url) {
        return retrofit.create(ServiceMetadata.class).getChannel(
                accessToken, rtsp_url).subscribeOn(Schedulers.io());
    }

    public Observable<CreateProgram> createProgram(
            String accessToken, String name, String description, String type,
            String category, String url, String schedule_date, String start_date, String end_date) {
        return retrofit.create(ServiceMetadata.class).createProgram(
                accessToken, name, description, type, category, url, schedule_date, start_date, end_date).subscribeOn(Schedulers.io());
    }

    public Observable<Common> bindProgram(
            String accessToken, String channel_id, String program_id) {
        return retrofit.create(ServiceMetadata.class).bindProgram(
                accessToken, channel_id, program_id).subscribeOn(Schedulers.io());
    }

    public Observable<Common> bindChannel(
            String accessToken, String channel_group_id, String channel_id) {
        return retrofit.create(ServiceMetadata.class).bindChannel(
                accessToken, channel_group_id, channel_id).subscribeOn(Schedulers.io());
    }

    public Observable<Common> unbindChannel(
            String accessToken, String channel_group_id, String channel_id) {
        return retrofit.create(ServiceMetadata.class).unbindChannel(
                accessToken, channel_group_id, channel_id).subscribeOn(Schedulers.io());
    }

    public Observable<Common> pushGroupProgram(
            String box_group_id, String program_id) {
        return retrofit.create(ServiceMetadata.class).pushGroupProgram(
                box_group_id, program_id).subscribeOn(Schedulers.io());
    }
}
