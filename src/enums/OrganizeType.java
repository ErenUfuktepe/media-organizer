package enums;

public enum OrganizeType {
    DAY("Day"),
    WEEK("Week"),
    MONTH("Month");

    private final String displayName;

    OrganizeType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static OrganizeType fromDisplayName(String displayName) {
        for (OrganizeType organizeType : OrganizeType.values()) {
            if (organizeType.getDisplayName().equalsIgnoreCase(displayName)) {
                return organizeType;
            }
        }
        return null;
    }
}
