package com.ngxial.classboxdemo.common.cloud.pojo;

public class Common {
    private String status;

    private String message;

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
        return "ClassPojo [message = " + message + ", status = " + status + "]";
    }
}
