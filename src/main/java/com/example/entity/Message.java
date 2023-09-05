package com.example.entity;

import lombok.Data;

/**
 * 用于接受客户端传来的消息
 */
@Data
public class Message {

    private String target;
    private Object message;

}
