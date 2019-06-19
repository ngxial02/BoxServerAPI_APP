package com.ngxial.classboxdemo.common.cloud.pojo;

public class BoxGroupInfo {
    private BoxGroup boxGroup;

    public BoxGroup getBoxGroup() {
        return boxGroup;
    }

    public void setBoxGroup(BoxGroup boxGroup) {
        this.boxGroup = boxGroup;
    }

    @Override
    public String toString() {
        return "ClassPojo [boxGroup = " + boxGroup + "]";
    }
}
