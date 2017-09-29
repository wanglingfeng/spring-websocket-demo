package com.joseph.demo.socket.domain;

/**
 * Created by lfwang on 2017/9/29.
 */
public class MessageResponse {
    
    private String message;
    
    public  MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
