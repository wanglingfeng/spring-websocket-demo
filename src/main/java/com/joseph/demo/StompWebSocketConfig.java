package com.joseph.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * EnableWebSocketMessageBroker注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思
 * Created by lfwang on 2017/9/29.
 */
@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * registerStompEndpoints方法表示注册STOMP协议的节点，并指定映射的URL
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 来注册STOMP协议节点，同时指定使用SockJS协议
        registry.addEndpoint("/endpointStomp").withSockJS();
    }

    /**
     * configureMessageBroker方法用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
    }
}
