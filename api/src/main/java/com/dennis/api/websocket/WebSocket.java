package com.dennis.api.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Dennis on 2018/12/23.
 */
@Component
@ServerEndpoint("/websocket/{code}")
public class WebSocket implements Comparable<WebSocket> {

    private final static Logger logger = LoggerFactory.getLogger(WebSocket.class);


    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, Session> sessionPool = new HashMap<String, Session>();
    private static Map<Session, WebSocket> webSocketPool = new HashMap<Session, WebSocket>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "code") String code) {
        this.session = session;
        webSockets.add(this);
        webSocketPool.put(session, this);
        sessionPool.put(code, session);
        logger.info("【websocket消息】:" + "客户端 " + code + " 连接成功, 【连接总数】:" + webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        logger.info("【websocket消息】连接断开, 【连接总数】:" + webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        logger.info("【websocket消息】:" + message);
    }

    @OnError
    public void onError(Session session, Throwable thr) {

    }

    public void sendAllMessage(String message) {
        for (WebSocket webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:" + message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendOneMessage(String code, String message) {
        Session session = sessionPool.get(code);
        if (session != null) {
            if (session.isOpen()) {
                try {
//                    session.getAsyncRemote().sendText(message);
                    session.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printSession(){

        logger.info("SessionPool: "+ sessionPool.size());
        logger.info("WebSocketPool: "+ webSocketPool.size());
        logger.info("WebSockets: "+ webSockets.size());

    }

    // 判断 Session 中是否有 code
    public boolean isExists(String code) {
        if (sessionPool.containsKey(code)) {
            Session session = sessionPool.get(code);
            if (session != null) {
                return true;
            }
        }
        return false;
    }

    // 手动关闭连接 清楚 session
    public boolean removeSession(String code) {

        if (sessionPool.containsKey(code)) {

            Session session = sessionPool.get(code);
            WebSocket webSocket = webSocketPool.get(session);

            sessionPool.remove(code);
            webSocketPool.remove(session);
            webSockets.remove(webSocket);

            return true;
        } else {
            return false;
        }
    }


    @Override
    public int compareTo(WebSocket o) {
        return o.session.hashCode() - this.session.hashCode();
    }
}
