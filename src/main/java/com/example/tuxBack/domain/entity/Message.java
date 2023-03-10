package com.example.tuxBack.domain.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Message {
    private String status;
    private String message;
    private Object data;


    public Message() {}

    public Message(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Message(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}