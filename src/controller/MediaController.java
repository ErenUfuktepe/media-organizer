package controller;

import enums.FileSystemType;
import enums.OrganizeType;
import model.File;
import model.FileSystemComponent;
import model.Folder;
import utils.FileUtils;

import java.util.List;


public class MediaController {

    private final List<String> extensions;

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
                    folder.add(new File(fileSystemObject.getName(), fileSystemObject.getAbsolutePath()));
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

    public void organizeMedia(String sourcePath, String targetPath, OrganizeType organizeType) {
        try {
            Folder folder = (Folder) buildFileSystemTree(sourcePath);
            for (FileSystemComponent component : folder.getFileSystemComponentList()) {
                if (component.getType().equals(FileSystemType.FILE)) {
                    // TODO: COPY FILE
                    System.out.println(component.getName());
                }
                else {
                    organizeMedia(component.getAbsolutePath(), targetPath, organizeType);
                }
            }
        }
        catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return null;
    }


}
