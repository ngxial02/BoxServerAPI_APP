package com.ngxial.classboxdemo.common.cloud.api;

import com.ngxial.classboxdemo.common.cloud.pojo.Common;
import com.ngxial.classboxdemo.common.cloud.pojo.CreateChannel;
import com.ngxial.classboxdemo.common.cloud.pojo.CreateProgram;
import com.ngxial.classboxdemo.common.cloud.pojo.Login;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ServiceMetadata {
    @FormUrlEncoded
    @POST("api/user/login")
    Observable<Login> login(@Field("account") String account,
                            @Field("password") String password);

    @FormUrlEncoded
    @POST("api/boxGroup/create")
    Observable<Common> createBoxGroup(
            @Field("accessToken") String accessToken,
            @Field("name") String name,
            @Field("description") String description,
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("api/channelGroup/create")
    Observable<Common> createChannelGroup(
            @Field("accessToken") String accessToken,
            @Field("name") String name,
            @Field("description") String description,
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("api/channel/create")
    Observable<CreateChannel> createChannel(
            @Field("accessToken") String accessToken,
            @Field("rtsp_url") String rtsp_url,
            @Field("streaming_key") String streaming_key,
            @Field("name") String name,
            @Field("description") String description,
            @Field("remark") String remark,
            @Field("vender_id") String vender_id
    );

    @FormUrlEncoded
    @POST("api/program/create")
    Observable<CreateProgram> createProgram(
            @Field("accessToken") String accessToken,
            @Field("name") String name,
            @Field("description") String description,
            @Field("type") String type,
            @Field("category") String category,
            @Field("url") String url,
            @Field("schedule_date") String schedule_date,
            @Field("start_date") String start_date,
            @Field("end_date") String end_date
    );

    @FormUrlEncoded
    @POST("api/channelMember/create")
    Observable<Common> bindProgram(
            @Field("accessToken") String accessToken,
            @Field("channel_id") String channel_id,
            @Field("program_id") String program_id
    );

    @FormUrlEncoded
    @POST("api/channelGroupMember/create")
    Observable<Common> bindChannel(
            @Field("accessToken") String accessToken,
            @Field("channel_group_id") String channel_group_id,
            @Field("channel_id") String channel_id
    );
}
