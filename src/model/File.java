package model;

import enums.FileSystemType;

public class File extends FileSystemComponent {

    private String name;
    private String absolutePath;
    private FileSystemType type;
    private String creationDate;

    public File(String name, String absolutePath) {
        setName(name);
        setAbsolutePath(absolutePath);
        setType(FileSystemType.FILE);
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
