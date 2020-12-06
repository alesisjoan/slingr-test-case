package com.github.alesisjoan.slingr.domain;

import javax.validation.constraints.NotBlank;

public class KeyValue {
    
    @NotBlank
    private final String key;
    
    @NotBlank
    private final String value;

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                "\"key\":" + "\"" + key + "\"" + ", " +
                "\"value\":" + "\"" + value + "\"" +
                "}";
    }
}
