package org.techtown.dagym.ui.chat;

public class ChatItem {

    String name;
    String message;
    String time;
    String profileUrl;

    public ChatItem(String name, String message, String time, String pofileUrl) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.profileUrl = pofileUrl;
    }

    //firebase DB에 객체로 값을 읽어올 때..
    //파라미터가 비어있는 생성자가 핑요함.
    public ChatItem() {
    }

    public ChatItem(String nickName, String message, String time) {
    }

    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}