package com.clairesense.app.component;
import com.clairesense.app.service.FileProcessorService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

@Component("myFileWatcher")
public class FileWatcher {

    private static final String DIRECTORY_TO_WATCH = "/Users/babadroga_92/Desktop/chica";


    private final FileProcessorService processorService;

    public FileWatcher(FileProcessorService processorService) {
        this.processorService = processorService;
    }

    @PostConstruct
    public void startWatching(){
        // Start the watcher in a new thread
        Thread thread = new Thread(this::watchDirectory);
        thread.setDaemon(true);
        thread.start();
    }

    private void watchDirectory(){
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();

            Path path = Paths.get(DIRECTORY_TO_WATCH);
            if(!Files.exists(path)){
                System.out.println("Directory does not exist: " + DIRECTORY_TO_WATCH);
                return;
            }
            path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

            System.out.println("Watching directory: " + path.toAbsolutePath());

            // Infinite loop to watch for events
            while (true) {
                WatchKey key = watchService.take(); // blocks until events occur

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    Path changedFile = ((WatchEvent<Path>) event).context();
                    if (kind == ENTRY_CREATE) {
                        processorService.handleFileCreated(path.resolve(changedFile).toString());
                        System.out.println("File created: " + path.resolve(changedFile));
                    } else if (kind == ENTRY_MODIFY) {
                        processorService.handleFileModified(path.resolve(changedFile).toString());
                        System.out.println("File modified: " + path.resolve(changedFile));
                    } else if (kind == ENTRY_DELETE) {
                        processorService.handleFileDeleted(path.resolve(changedFile).toString());
                    }
                }

                boolean valid = key.reset(); // reset the key to continue watching
                if (!valid) {
                    break; // directory no longer accessible
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
