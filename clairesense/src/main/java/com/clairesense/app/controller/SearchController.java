package com.clairesense.app.controller;

import com.clairesense.app.entity.FileChunk;
import com.clairesense.app.service.RetrievalService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final RetrievalService retrievalService;

    public SearchController(RetrievalService retrievalService) {
        this.retrievalService = retrievalService;
    }

    @PostMapping
    public List<Map<String, Object>> search(@RequestBody Map<String, String> body) {
        String query = body.getOrDefault("query", "");
        int topK = 5; // you can make this configurable later

        List<FileChunk> chunks = retrievalService.findRelevantChunks(query, topK);

        return chunks.stream()
                .map(chunk -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("fileName", chunk.getFile().getFileName());
                    map.put("filePath", chunk.getFile().getFilePath());
                    map.put("chunkIndex", chunk.getChunkIndex());
                    String text = chunk.getText();
                    map.put("textPreview", text.substring(0, Math.min(200, text.length())));
                    return map;
                })
                .toList();
    }
}
