package com.dennisiluma.springbootCRUD.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public class Person {
    private UUID id;
    @NonNull //here we are saying this field should contain something. @NotBlank is more better because empty string is not null but it's blank
    private String name;

    /**@JsonProperty("id") means we should map any json object request we'll be sending or coming in using this class with name id to id */
    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    public UUID getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}
