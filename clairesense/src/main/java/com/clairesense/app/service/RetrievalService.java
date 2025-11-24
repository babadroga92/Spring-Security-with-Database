package com.clairesense.app.service;

import com.clairesense.app.entity.FileChunk;
import com.clairesense.app.repository.FileChunkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RetrievalService {

    private final EmbeddingService embeddingService;
    private final FileChunkRepository fileChunkRepository;

    public RetrievalService(EmbeddingService embeddingService, FileChunkRepository fileChunkRepository) {
        this.embeddingService = embeddingService;
        this.fileChunkRepository = fileChunkRepository;
    }

    /*
        Find the topK chunks that are most similar in meaning to the query.
    */
    public List<FileChunk> findRelevantChunks(String query, int topK){
        // Embed the user's query
        float[] queryEmbedding = embeddingService.embed(query);

        // Fetch chunks from the DB
        List<FileChunk> allChunks = fileChunkRepository.findAll();

        // Score each chunk by similarity
        List<ScoredChunk> scoredChunks = new ArrayList<>();

        for (FileChunk chunk : allChunks){
            if (chunk.getEmbedding() == null || chunk.getEmbedding().isBlank()) continue;

            float[] chunkVector = parseEmbeddingString(chunk.getEmbedding());
            double score = cosineSimilarity(queryEmbedding, chunkVector);

            scoredChunks.add(new ScoredChunk(chunk, score));
        }

        return scoredChunks.stream()
            .sorted(Comparator.comparingDouble(ScoredChunk::score).reversed())
            .limit(topK)
            .map(ScoredChunk::chunk)
            .toList();
    }

    private float[] parseEmbeddingString(String embeddingString) {
        String[] parts = embeddingString.split(",");
        float[] vector = new float[parts.length];

        for (int i = 0; i < parts.length; i++) {
            vector[i] = Float.parseFloat(parts[i]);
        }

        return vector;
    }

    private double cosineSimilarity(float[] a, float[] b) {
        int len = Math.min(a.length, b.length); // should be equal, but just in case

        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int i = 0; i < len; i++) {
            dot += a[i] * b[i];
            normA += a[i] * a[i];
            normB += b[i] * b[i];
        }

        if (normA == 0.0 || normB == 0.0) {
            return 0.0; // avoid division by zero
        }

        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    // Small helper record to keep a chunk and its similarity together
    private record ScoredChunk(FileChunk chunk, double score) {}
}
