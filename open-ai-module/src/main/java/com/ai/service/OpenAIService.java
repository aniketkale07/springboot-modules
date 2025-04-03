package com.ai.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
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

    
    public String getResponseOptions(String prompt){
        ChatResponse chatResponse= chatModel.call(
            new Prompt(prompt, 
            OpenAiChatOptions.builder()
                .model("gpt-4o")    // .withModel --> changed to .model
                .temperature(4.0d)  // .withTemperature to temperature --> cast long to double
                .build()
            )
        );
        return chatResponse.getResult().getOutput().getText();
    }
}
