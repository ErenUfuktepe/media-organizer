package controller;

import enums.FileSystemType;
import enums.OrganizeType;
import model.File;
import model.FileSystemComponent;
import model.Folder;
import service.FileTaskManager;
import utils.FileUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.InvalidObjectException;
import java.util.List;


public class MediaController {

    private final static String DESKTOP_LOCATION = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "/media-organizer";
    private final List<String> extensions;
    private FileTaskManager fileTaskManager = new FileTaskManager(5);

    public MediaController(List<String> extensions) {
        this.extensions = extensions;
    }

    private FileSystemComponent buildFileSystemTree(String path) {
        try {
            java.io.File mainFolder = FileUtils.getFileObject(path);
            Folder folder = new Folder(mainFolder.getName(), mainFolder.getAbsolutePath());

            for (java.io.File fileSystemObject : mainFolder.listFiles()) {
                if (fileSystemObject.isFile() && extensions.contains(getFileExtension(fileSystemObject.getName()))) {
                    File file = new File(fileSystemObject.getName(), fileSystemObject.getAbsolutePath());
                    file.setCreationDate(FileUtils.getCreationDate(fileSystemObject));
                    folder.add(file);
                }
                else if (fileSystemObject.isDirectory()) {
                    folder.add(buildFileSystemTree(fileSystemObject.getAbsolutePath()));
                }
            }
            return folder;
        }
        catch (Exception exception) {
            throw exception;
        }
    }

    public void organizeMedia(String sourcePath, OrganizeType organizeType) {
        try {
            Folder folder = (Folder) buildFileSystemTree(sourcePath);
            for (FileSystemComponent component : folder.getFileSystemComponentList()) {
                if (component.getType().equals(FileSystemType.FILE)) {
                    String destination = DESKTOP_LOCATION + "/" + generateFolderLocation((File) component, organizeType);
                    fileTaskManager.copyFile(component, destination);
                }
                else {
                    organizeMedia(component.getAbsolutePath(), organizeType);
                }
            }
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private String generateFolderLocation(File file, OrganizeType organizeType) {
        try {
            switch (organizeType) {
                case DAY:
                    return file.getCreationDate();
                case WEEK:
                    int day = Integer.parseInt(file.getCreationDate().substring(6, 8), 10);
                    if (day <= 7) {
                        return file.getCreationDate().substring(0, 4) + file.getCreationDate().substring(4, 6) + "01-07";
                    }
                    else if (day > 7 && day <= 14) {
                        return file.getCreationDate().substring(0, 4) + file.getCreationDate().substring(4, 6) + "08-14";
                    }
                    else if (day > 14 && day <= 21) {
                        return file.getCreationDate().substring(0, 4) + file.getCreationDate().substring(4, 6) + "15-21";
                    }
                    else {
                        return file.getCreationDate().substring(0, 4) + file.getCreationDate().substring(4, 6) + "22-31";
                    }
                case MONTH:
                    return file.getCreationDate().substring(0,6);
                default:
                    throw new InvalidObjectException("Organize type is not valid");
            }
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public void shutdown() {
        fileTaskManager.shutdown();
    }

    public String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return null;
    }


}
