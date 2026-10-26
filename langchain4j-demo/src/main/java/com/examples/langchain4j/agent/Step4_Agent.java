package com.examples.langchain4j.agent;

import com.examples.langchain4j.service.Assistant;
import com.examples.langchain4j.tool.CalculatorTools;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;

public class Step4_Agent {

    public static void main(String[] args) {
        ChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .tools(new CalculatorTools())   // 注册工具
                .build();

        System.out.println(assistant.chat("user-1", "北京今天天气怎么样？如果晴天，帮我算一下 123 + 456"));
    }


}
