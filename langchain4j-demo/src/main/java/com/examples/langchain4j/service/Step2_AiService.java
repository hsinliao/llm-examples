package com.examples.langchain4j.service;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class Step2_AiService {

    public static void main(String[] args) {
        ChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .chatMemoryProvider(id -> MessageWindowChatMemory.withMaxMessages(10))
                .build();

        System.out.println(assistant.chat("user-1", "我叫小明"));
        System.out.println(assistant.chat("user-1", "我叫什么？"));  // 能记住
        System.out.println(assistant.chat("user-2", "我叫什么？"));  // 另一个会话，不知道
    }
}
