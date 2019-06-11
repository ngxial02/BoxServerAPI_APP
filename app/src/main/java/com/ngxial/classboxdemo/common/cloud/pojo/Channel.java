package com.ngxial.classboxdemo.common.cloud.pojo;

public class Channel {

    private String cover;

    private String vender_id;

    private String remark;

    private String description;

    private String name;

    private String streaming_key;

    private String rtsp_url;

    private String _id;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVender_id() {
        return vender_id;
    }

    public void setVender_id(String vender_id) {
        this.vender_id = vender_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreaming_key() {
        return streaming_key;
    }

    public void setStreaming_key(String streaming_key) {
        this.streaming_key = streaming_key;
    }

    public String getRtsp_url() {
        return rtsp_url;
    }

    public void setRtsp_url(String rtsp_url) {
        this.rtsp_url = rtsp_url;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [_id = " + _id + ", name = " + name + ", description = " + description + "]";
    }
}