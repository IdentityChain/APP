package com.project.isc.iscdbserver.viewentity;

public class FutureLettersVOSimple {

    private String flId;

    // 用户内容
    private String content;

    //时间
    private String totime;

    //写入时间
    private String sender;

    public String getFlId() {
        return flId;
    }

    public void setFlId(String flId) {
        this.flId = flId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
