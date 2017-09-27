package com.joseph.demo.socket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by lfwang on 2017/9/27.
 */
public class MessageHandler extends TextWebSocketHandler {

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    /**
     * 成功建立连接
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        userOnline(session);
    }

    /**
     * 接收到消息处理
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("来自客户端的消息, " + message);
        sendToAll(message);
    }

    /**
     * 处理异常
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("发生错误");
        
        if (session.isOpen()) session.close();
        userOffline(session);
        
        exception.printStackTrace();
    }

    /**
     * 连接关闭后
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        userOffline(session);
    }
    
    private void sendToAll(TextMessage message) {
            sessions.forEach(s -> {
                if (s.isOpen()) try {
                    s.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }
    
    private int getOnlineCount() {
        return sessions.size();
    }
    
    private void userOnline(WebSocketSession session) {
        sessions.add(session);
        System.out.println("有新连接加入，当前在线人数为: " + getOnlineCount());
    }
    
    private void userOffline(WebSocketSession session) {
        sessions.remove(session);
        System.out.println("有连接关闭，当前在线人数为: " + getOnlineCount());
    }
}
