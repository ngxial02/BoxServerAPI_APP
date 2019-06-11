package com.ngxial.classboxdemo.common.cloud.pojo;

public class CreateChannel {
    private ChannelInfo content;

    private String status;

    private String message;

    public ChannelInfo getContent() {
        return content;
    }

    public void setContent(ChannelInfo content) {
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
