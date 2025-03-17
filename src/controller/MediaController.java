package controller;

import enums.FileSystemType;
import enums.OrganizeType;
import model.FileSystemComponent;
import model.Folder;
import model.File;
import utils.FileUtils;


public class MediaController {

    public MediaController() {

    }

    private FileSystemComponent buildFileSystemTree(String path) {
        try {
            java.io.File mainFolder = FileUtils.getFileObject(path);
            Folder folder = new Folder(mainFolder.getName(), mainFolder.getAbsolutePath());

            for (java.io.File fileSystemObject : mainFolder.listFiles()) {
                if (fileSystemObject.isFile()) {
                    folder.add(new File(fileSystemObject.getName(), fileSystemObject.getAbsolutePath()));
                }
                else {
                    folder.add(buildFileSystemTree(fileSystemObject.getAbsolutePath()));
                }
            }
            return folder;
        }
        catch (Exception exception) {
            throw exception;
        }
    }

    public void organizeMedia(String path, OrganizeType organizeType) {
        try {
            Folder folder = (Folder) buildFileSystemTree(path);
            for (FileSystemComponent component : folder.getFileSystemComponentList()) {
                if (component.getType().equals(FileSystemType.FILE)) {
                    // TODO: COPY FILE
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



}
