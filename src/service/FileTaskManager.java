package service;

import model.FileSystemComponent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileTaskManager {
    private final ExecutorService executorService;

    public FileTaskManager(int maxThreads) {
        executorService = Executors.newFixedThreadPool(maxThreads);
    }

    public void copyFile(FileSystemComponent source, String destination) {
        try {
            Path targetPath = Paths.get(destination);
            Files.createDirectories(targetPath);
            Path sourcePath = Paths.get(source.getAbsolutePath());
            executorService.submit(new FileCopyTask(sourcePath, targetPath.resolve(sourcePath.getFileName())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.HOURS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static class FileCopyTask implements Runnable {

        private Path sourcePath;
        private Path targetPath;

        public FileCopyTask(Path sourcePath, Path targetPath) {
            this.sourcePath = sourcePath;
            this.targetPath = targetPath;
        }

        @Override
        public void run() {
            try{
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied: " + sourcePath.getFileName());
            }
            catch (IOException ioException) {
                System.err.println("Failed to copy " + sourcePath + " -> " + targetPath + ": " + ioException.getMessage());
            }

        }
    }
}
