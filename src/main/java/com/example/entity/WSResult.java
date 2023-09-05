package com.example.entity;

import lombok.Data;

@Data
public class WSResult {
    private boolean broadcast;   // 是否为广播消息
    private String from;        // 消息的来源
    private Object msg; // 在线用户列表
//    private Integer onlineUserCount;
}
