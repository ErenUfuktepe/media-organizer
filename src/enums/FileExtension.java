package enums;

public enum FileExtension {
    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png");

    private String displayName;

    FileExtension(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static FileExtension fromDisplayName(String displayName) {
        for (FileExtension fileExtension : FileExtension.values()) {
            if (fileExtension.getDisplayName().equalsIgnoreCase(displayName)) {
                return fileExtension;
            }
        }
        return null;
    }

}
