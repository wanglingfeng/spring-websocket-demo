package com.joseph.demo.socket;

import com.joseph.demo.socket.domain.MessageRequest;
import com.joseph.demo.socket.domain.MessageResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * WebSocket为浏览器和服务器之间提供了双工异步通信功能，也就是说我们可以利用浏览器给服务器发送消息，服务器也可以给浏览器发送消息，
 * 目前主流浏览器的主流版本对WebSocket的支持都算是比较好的，
 * 但是在实际开发中使用WebSocket工作量会略大，而且增加了浏览器的兼容问题，
 * 这种时候我们更多的是使用WebSocket的一个子协议stomp，利用它来快速实现我们的功能
 * Created by lfwang on 2017/9/29.
 */
@Controller
public class WsController {

    /**
     * say方法上添加的@MessageMapping注解和我们之前使用的RequestMapping类似。
     * SendTo注解表示当服务器有消息需要推送的时候，会对订阅了SendTo中路径的浏览器发送消息
     * @param request
     * @return
     */
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public MessageResponse say(MessageRequest request) {
        System.out.println(request.getName());
        return new MessageResponse("welcome, " + request.getName());
    }
}
