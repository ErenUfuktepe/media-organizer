package model;

import enums.FileSystemType;

import java.util.ArrayList;
import java.util.List;

public class Folder extends FileSystemComponent {

    private String name;
    private String absolutePath;
    private FileSystemType type;
    private List<FileSystemComponent> fileSystemComponentList  = new ArrayList<>();

    public Folder(String name, String absolutePath) {
        setName(name);
        setAbsolutePath(absolutePath);
        setType(FileSystemType.FOLDER);
    }

    @Override
    public void add(FileSystemComponent fileSystemComponent) {
        fileSystemComponentList.add(fileSystemComponent);
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Override
    public FileSystemType getType() {
        return type;
    }

    public void setType(FileSystemType type) {
        this.type = type;
    }

    public List<FileSystemComponent> getFileSystemComponentList() {
        return fileSystemComponentList;
    }
}
