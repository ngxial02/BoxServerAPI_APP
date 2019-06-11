package com.ngxial.classboxdemo.common.cloud;

import com.ngxial.classboxdemo.BuildConfig;

public class ServiceConfig {
    private String CLOUD_URI;
    private String IP = BuildConfig.SERVER_DOMAIN;

    private static ServiceConfig instance;

    public static synchronized ServiceConfig getInstance() {
        if (instance == null) {
            instance = new ServiceConfig();
        }
        return instance;
    }

    private ServiceConfig() {
        CLOUD_URI = "http://" + IP + ":8888";
    }

    public String getIp() {
        return IP;
    }

    public void setIp(String ip) {
        IP = ip;
        CLOUD_URI = "http://" + IP + ":8888";
    }

    public String getCloudUri() {
        return CLOUD_URI;
    }
}
