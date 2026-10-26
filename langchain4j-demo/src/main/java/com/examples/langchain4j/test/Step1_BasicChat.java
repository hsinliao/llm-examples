package com.examples.langchain4j.test;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class Step1_BasicChat {

    public static void main(String[] args) {
        ChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))   // 走环境变量，不要硬编码
                .modelName("gpt-4o-mini")
                // .baseUrl("https://api.deepseek.com")    // 换 DeepSeek 时取消注释
                .build();

        String answer = model.chat("用一句话解释什么是 RAG");
        System.out.println(answer);
    }
}
