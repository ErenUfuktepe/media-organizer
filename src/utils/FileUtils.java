package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {

    public FileUtils() {

    }

    public static boolean isValidDirectory(String path) {
        return new File(path).exists();
    }

    public static File getFileObject(String path) {
        try {
            if (isValidDirectory(path)) {
                return new File(path);
            }
            else {
                throw new InvalidObjectException("File path does not exists.");
            }
        }
        catch (InvalidObjectException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String getCreationDate(File file){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            BasicFileAttributes fileAttribute = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            return dateFormat.format(new Date(fileAttribute.creationTime().toMillis()));
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

}
