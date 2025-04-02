package com.ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.service.OpenAIService;

@RestController
public class ChatController {
    
    private final OpenAIService openAIService;

    public  ChatController(OpenAIService openAIService){
        this.openAIService=openAIService;
       
    }

    @GetMapping("/ask-ai")
    public String chatResponse(String prompt){
return openAIService.getResponse(prompt);
    }

}
