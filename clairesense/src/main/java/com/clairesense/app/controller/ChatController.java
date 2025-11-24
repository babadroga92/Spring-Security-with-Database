package com.clairesense.app.controller;

import com.clairesense.app.entity.FileChunk;
import com.clairesense.app.service.RetrievalService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;
    private final RetrievalService retrievalService;

    public ChatController(ChatClient.Builder chatClientBuilder,
                          RetrievalService retrievalService) {
        // use the autoconfigured builder to build a client
        this.chatClient = chatClientBuilder.build();
        this.retrievalService = retrievalService;
    }

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> body) {
        String userMessage = body.getOrDefault("message", "");

        // Retrieve top K relevant chunks from vector store
        int topK = 5;
        List<FileChunk> chunks = retrievalService.findRelevantChunks(userMessage, topK);

        // Build context string from those chunks
        StringBuilder contextBuilder = new StringBuilder();

        for(FileChunk chunk : chunks) {
            contextBuilder
                    .append("File: ")
                    .append(chunk.getFile().getFileName())
                    .append(" (chunk ")
                    .append(chunk.getChunkIndex())
                    .append(")\n")
                    .append(chunk.getText())
                    .append("\n\n");
        }

        String context = contextBuilder.toString();

        // Build system prompt that instructs the model how to use the context

        String systemPrompt = """
                You are a helpful assistant that answers questions using the provided context
                from the user's local documents.

                Use ONLY the information in the context below. If the answer is not clearly
                present in the context, say "I don't know based on the provided documents."

                Context:
                """ + context;


        // Call the model with system prompt + user message
        String assistant = this.chatClient
                .prompt()
                .system(systemPrompt)
                .user(userMessage)
                .call()
                .content();

        // Return only the reply
        return Map.of("reply", assistant);
    }
}
