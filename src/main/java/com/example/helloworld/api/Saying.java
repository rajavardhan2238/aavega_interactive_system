package com.example.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Created by vbathula on 9/25/16.
 */
public class Saying {

    private long id;

    @Length(max=3)
    private String content;

    public Saying(long id, String content){
        this.id=id;
        this.content=content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
