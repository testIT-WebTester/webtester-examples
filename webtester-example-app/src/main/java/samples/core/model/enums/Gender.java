package samples.core.model.enums;

public enum Gender {

    NONE(""), FEMALE("f"), MALE("m");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
