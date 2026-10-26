package com.examples.spring.controller;

import com.examples.spring.tool.WeatherTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    ChatController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("你是一个简洁的技术助手，回答不超过三句话。")
                .defaultTools(new WeatherTools())
                .build();
    }

    @GetMapping("/chat")
    String chat(@RequestParam String input) {
        return chatClient.prompt()
                .user(input)
                .call()
                .content();
    }

    @GetMapping(value = "/chat/stream", produces = "text/plain;charset=UTF-8")
    Flux<String> stream(String input) {
        return chatClient.prompt().user(input).stream().content();
    }
}
