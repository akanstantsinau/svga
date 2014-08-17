package com.vivarium.svga.model;

/**
 * Created by neurons on 8/16/14.
 */
public class PreserveAspectRatio {
    private boolean defer;
    private Align xAlign;
    private Align yAlign;
    private MeetOrSlice meetOrSlice;

    public boolean isDefer() {
        return defer;
    }

    public void setDefer(boolean defer) {
        this.defer = defer;
    }

    public Align getxAlign() {
        return xAlign;
    }

    public void setxAlign(Align xAlign) {
        this.xAlign = xAlign;
    }

    public MeetOrSlice getMeetOrSlice() {
        return meetOrSlice;
    }

    public void setMeetOrSlice(MeetOrSlice meetOrSlice) {
        this.meetOrSlice = meetOrSlice;
    }



    public Align getyAlign() {
        return yAlign;
    }

    public void setyAlign(Align yAlign) {
        this.yAlign = yAlign;
    }


    public enum Align{MIN, MID, MAX}
    public enum MeetOrSlice{MEET, SLICE}
}
