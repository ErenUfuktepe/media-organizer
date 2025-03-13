package model;

import enums.FileSystemType;

public abstract class FileSystemComponent {
    // Name of the component
    private String name;
    // Component location
    private String directory;
    // Component type
    private FileSystemType type;

    FileSystemComponent(String name, String directory) {
        this.name = name;
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public FileSystemComponent setName(String name) {
        this.name = name;
        return this;
    }

    public String getDirectory() {
        return directory;
    }

    public FileSystemComponent setDirectory(String directory) {
        this.directory = directory;
        return this;
    }

    public FileSystemType getType() {
        return type;
    }

    public FileSystemComponent setType(FileSystemType type) {
        this.type = type;
        return this;
    }
}
