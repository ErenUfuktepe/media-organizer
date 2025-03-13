package model;

import enums.FileSystemType;

public class Folder extends FileSystemComponent {
    Folder(String name, String directory) {
        super(name, directory);
        this.setType(FileSystemType.FOLDER);
    }
}
