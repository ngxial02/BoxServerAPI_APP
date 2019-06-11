package com.ngxial.classboxdemo.common.cloud.pojo;

public class ProgramInfo {
    private Program program;

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "ClassPojo [program = " + program + "]";
    }
}
