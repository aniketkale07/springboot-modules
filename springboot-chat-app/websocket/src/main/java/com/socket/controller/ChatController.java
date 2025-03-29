package com.socket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.socket.model.ChatMessage;
import com.socket.model.UserNotification;

@Controller
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/topic/return-message")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage; // Broadcast message to all users
    }

    @MessageMapping("/user-joined")
    @SendTo("/topic/user-joined")
    public ChatMessage userJoined(ChatMessage chatMessage) {
        return chatMessage; // Notify all users when someone joins
    }

     @MessageMapping("/user-left")
    @SendTo("/topic/user-left")
    public UserNotification userLeft(UserNotification notification) {
        return notification;  // Sends "user left" notification
    }
}
