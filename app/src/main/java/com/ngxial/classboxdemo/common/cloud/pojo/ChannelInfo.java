package com.ngxial.classboxdemo.common.cloud.pojo;

public class ChannelInfo {
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "ClassPojo [channel = " + channel + "]";
    }
}
