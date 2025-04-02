package com.ai.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {
    
    private final ChatModel chatModel;

    public OpenAIService(ChatModel chatModel){
        this.chatModel=chatModel;
    }

    // service method who can easily to get back response to the controller
    public String getResponse(String prompt){
        return chatModel.call(prompt);
        
    }
}
