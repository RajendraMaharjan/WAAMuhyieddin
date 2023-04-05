package edu.miu.waalab.domain.value.enums;

public enum SearchPostTypes {
    TITLE("title"), CONTENT("content"), AUTHOR("author");

    String value;

    SearchPostTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
