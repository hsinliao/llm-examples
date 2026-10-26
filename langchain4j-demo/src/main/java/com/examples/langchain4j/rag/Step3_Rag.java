package com.examples.langchain4j.rag;

import com.examples.langchain4j.service.Assistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;

public class Step3_Rag {

    public static void main(String[] args) {
        ChatModel model = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("gpt-4o-mini")
                .build();

        var store = RagConfig.buildStore(model);

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(model)
                .contentRetriever(EmbeddingStoreContentRetriever.builder()
                        .embeddingStore(store)
                        .embeddingModel()  // 与建库时同一个 EmbeddingModel
                        .maxResults(2)          // 取最相近的 2 块
                        .minScore(0.7)          // 相似度阈值，过滤低质量片段
                        .build())
                .build();

        System.out.println(assistant.chat("user-1", "LangChain4j 最低要求什么 JDK 版本？"));
    }
}
