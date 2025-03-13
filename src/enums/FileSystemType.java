package enums;

public enum FileSystemType {
    FILE("File"),
    FOLDER("Folder");

    private final String displayName;

    FileSystemType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
