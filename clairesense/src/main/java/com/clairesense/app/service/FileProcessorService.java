package com.clairesense.app.service;

import com.clairesense.app.entity.FileChunk;
import com.clairesense.app.entity.FileEntity;
import com.clairesense.app.repository.FileChunkRepository;
import com.clairesense.app.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class FileProcessorService {

    private static final int CHUNK_SIZE = 1000;

    private final FileRepository fileRepository;
    private final FileChunkRepository fileChunkRepository;
    private final EmbeddingService embeddingService;

    public FileProcessorService(FileRepository fileRepository, FileChunkRepository fileChunkRepository, EmbeddingService embeddingService) {
        this.fileRepository = fileRepository;
        this.fileChunkRepository = fileChunkRepository;
        this.embeddingService = embeddingService;
    }

    // TODO: To inject Ollama client here

    public void handleFileCreated(String filePath){
        try {
            // Read file content
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Extract file name from path
            String fileName = Paths.get(filePath).getFileName().toString();

            // Save to database
            FileEntity fileEntity = new FileEntity(fileName, filePath, content);
            fileRepository.save(fileEntity);

            createChunksForFile(fileEntity, content);

            System.out.println("File saved to database: " + fileName);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Processing new file: " + filePath);

    }

    public void handleFileModified(String filePath){
        fileRepository.findByFilePath(filePath).ifPresent(fileEntity -> {
            try{
                String content = new String(Files.readAllBytes(Paths.get(filePath)));
                fileEntity.setContent(content);
                fileEntity.setUpdatedAt(LocalDateTime.now());

                if (fileEntity.getChunks() != null){
                    fileEntity.getChunks().clear();
                }

                fileRepository.save(fileEntity);
                createChunksForFile(fileEntity, content);
                System.out.println("File updated in database: " + fileEntity.getFileName());

            }catch (IOException e){
                e.printStackTrace();
            }
        });

        System.out.println("File modified: " + filePath);
    }
    public void handleFileDeleted(String filePath){
        fileRepository.findByFilePath(filePath).ifPresent(fileEntity -> {
            fileRepository.delete(fileEntity);
            System.out.println("File deleted from database: " + fileEntity.getFileName());
        });
        System.out.println("File deleted: " + filePath);
    }

    private void createChunksForFile(FileEntity fileEntity, String content){
        int length = content.length();
        int chunkIndex = 0;

        for (int start = 0; start < length; start += CHUNK_SIZE){
            int end = Math.min(start + CHUNK_SIZE, length);
            String chunkText = content.substring(start, end);

            FileChunk chunk = new FileChunk(fileEntity, chunkIndex, chunkText);


            // Get embedding for this chunk from Ollama via Spring AI
            float[] vector = embeddingService.embed(chunkText);

            // Convert float[] to a simple string
            String embeddingString = floatArrayToString(vector);
            chunk.setEmbedding(embeddingString);

            // save chunk with text and embedding
            fileChunkRepository.save(chunk);

            chunkIndex++;
        }
        System.out.println("Created " + chunkIndex + " chunks for file: " + fileEntity.getFileName());
    }
    private String floatArrayToString(float[] vector) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < vector.length; i++){
            if (i > 0){
                sb.append(',');
            }
            sb.append(vector[i]);
        }
        return sb.toString();
    }
}
