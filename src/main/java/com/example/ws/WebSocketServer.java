package com.example.ws;

import javax.servlet.http.HttpSession;
import javax.websocket.*;

import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson2.JSON;
import com.example.config.HttpSessionConfig;
import com.example.entity.Message;
import org.springframework.stereotype.Component;

import com.example.entity.WSResult;


@Component
@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfig.class)
public class WebSocketServer {

    // 存放所有的创建的 ws 实例
    static Map<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    // 保存当前 websocket 的 Session
    private Session session;
    private HttpSession httpSession;

    /**
     * 连接建立成功调用
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        String name = (String) httpSession.getAttribute("name");
        // 解决重复链接
        if (webSocketMap.containsKey(name)) {
            webSocketMap.get(name).session.close();
            webSocketMap.remove(name);
        }
        this.session = session;
        this.httpSession = httpSession;
        webSocketMap.put(name, this);
        // 用户登陆时向所有用户发送目前所有登陆用户的信息
        broadcast();
    }

    /**
     * 连接关闭调用
     */
    @OnClose
    public void onClose() throws IOException {
        Object name = httpSession.getAttribute("name");
        webSocketMap.remove((String)name);
        broadcast();
    }
    /**
     * 收到客户端消息后调用
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
        String name = (String) httpSession.getAttribute("name");
        System.out.println(name);
        // 设置消息
        Message msg = JSON.parseObject(message, Message.class);
        String res = createMessage(false, name, msg.getMessage());
        WebSocketServer target = webSocketMap.get(msg.getTarget());
        target.session.getBasicRemote().sendText(res);
    }

    /**
     * 出现异常时调用
     */
    @OnError
    public void onError(Throwable error) {
        System.out.println("链接出现异常!");
    }

    public void broadcast() throws IOException {
        for (WebSocketServer ws : webSocketMap.values()) {
            // 广播消息 返回所有在线的用户名称
            String message = createMessage(true, null, webSocketMap.keySet());
            try {
                // 可能会引发异常："不会发送消息，因为 WebSocket 会话已关闭"
                ws.session.getBasicRemote().sendText(message);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println(webSocketMap);
            }
        }
    }

    /**
     * 创建消息
     * @param isBroadcast
     * @param from
     * @param message
     * @return String
     */
    public String createMessage(boolean isBroadcast, String from, Object message) {
        WSResult result = new WSResult();
        result.setBroadcast(isBroadcast);
        if (from != null) result.setFrom(from);
        result.setMsg(message);
        return JSON.toJSONString(result);
    }

}