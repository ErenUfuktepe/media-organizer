package service;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileTaskManager {
    private final ExecutorService executorService;

    public FileTaskManager(int maxThreads) {
        executorService = Executors.newFixedThreadPool(maxThreads);
    }

    public void copyFile(File source, File destination) {
        executorService.execute(new FileTask());
    }

    public void shutdown() {
        executorService.shutdown();
    }

    private static class FileTask implements Runnable {

        public FileTask() {

        }

        @Override
        public void run() {

        }
    }
}
