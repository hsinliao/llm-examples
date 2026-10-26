package com.examples.langchain4j.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Assistant {

    @SystemMessage("你是一个简洁的 Java 技术助手，回答不超过三句话。")
    String chat(@MemoryId String userId, @UserMessage String message);


}
