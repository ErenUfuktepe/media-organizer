package utils;

import enums.OrganizeType;

import java.io.*;

public class FileUtils {

    // TODO : Set the target as desktop with specific name.
    private final static String TARGET_PATH = "";

    public FileUtils() {

    }

    public static boolean isValidDirectory(String path) {
        try {
            File fileSystemComponent =  new File(path);
            return (fileSystemComponent.exists() && fileSystemComponent.isDirectory());
        }
        catch (Exception exception) {
            throw exception;
        }
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

    public static boolean copyFile(String path, OrganizeType organizeType) {
        try{
            File file = getFileObject(path);
            switch (organizeType){
                case DAY:
                    break;
                case WEEK:
                    break;
                case MONTH:
                    break;
                default:
                    throw new InvalidObjectException("Invalid organize type.");
            }

            return true;
        }
        catch (InvalidObjectException exception) {
            throw new RuntimeException(exception);
        }
    }

}
