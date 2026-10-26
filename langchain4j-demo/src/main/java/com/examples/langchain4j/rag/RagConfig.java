package com.examples.langchain4j.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.util.List;

public class RagConfig {

    public static EmbeddingStore<TextSegment> buildStore(ChatModel chatModel) {
        // Embedding 模型，用于把文本转成向量
        EmbeddingModel embeddingModel = OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName("text-embedding-3-small")
                .build();

        EmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();

        // 模拟你的私有文档
        List<Document> docs = List.of(
                Document.from("LangChain4j 是一个面向 Java 的 LLM 应用框架，最低要求 JDK 17。"),
                Document.from("Spring AI 2.x 基于 Spring Boot 4 和 Spring Framework 7。"),
                Document.from("RAG 的全称是 Retrieval-Augmented Generation，即检索增强生成。")
        );

        EmbeddingStoreIngestor.builder()
                .documentSplitter(DocumentSplitters.recursive(300, 50))  // 切块
                .embeddingModel(embeddingModel)
                .embeddingStore(store)
                .build()
                .ingest(docs);

        return store;
    }


}
