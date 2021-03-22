package org.techtown.dagym.ui.chat;

public class ChatItem {

    String name;
    String message;
    String time;

    public ChatItem(String name, String message, String time) {
        this.name = name;
        this.message = message;
        this.time = time;
    }

    //firebase DB에 객체로 값을 읽어올 때 파라미터가 비어있는 생성자가 필요
    public ChatItem() {
    }

    public ChatItem(String name, String message) {
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