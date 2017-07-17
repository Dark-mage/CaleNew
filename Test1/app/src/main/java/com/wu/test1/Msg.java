package com.wu.test1;

/**
 * Created by wu on 2017/7/17.
 */

public class Msg {
    public static final int MSG_RECEIVED = 1;
    public static final int MSG_SEND=0;
    private String msg;
    private int type;

    public Msg(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
