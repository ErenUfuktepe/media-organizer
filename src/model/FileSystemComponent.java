package model;

import enums.FileSystemType;

public abstract class FileSystemComponent {

    public void add(FileSystemComponent fileSystemComponent) {
        throw new UnsupportedOperationException();
    }

    public String getAbsolutePath(){
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public FileSystemType getType() {
        throw new UnsupportedOperationException();
    }

}
