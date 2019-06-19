package com.ngxial.classboxdemo.common.cloud.pojo;

public class CreateChannelGroup {
    private ChannelGroupInfo content;

    private String status;

    private String message;

    public ChannelGroupInfo getContent() {
        return content;
    }

    public void setContent(ChannelGroupInfo content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ClassPojo [content = " + content + ", status = " + status + "]";
    }
}
