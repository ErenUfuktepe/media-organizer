package model;

import enums.FileExtension;
import enums.FileSystemType;

import java.time.LocalDateTime;
import java.util.List;

public class File extends FileSystemComponent {
    private LocalDateTime createdDateTime;
    private double size;
    private FileExtension fileExtension;

    File(String name, String directory) {
        super(name, directory);
        this.setType(FileSystemType.FILE);

        String[] nameParts = name.split(".");
        fileExtension = FileExtension.fromDisplayName(nameParts[nameParts.length - 1]);
        System.out.println(fileExtension);
    }
}
