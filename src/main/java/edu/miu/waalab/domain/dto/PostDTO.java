package edu.miu.waalab.domain.dto;

import lombok.Data;

@Data
public class PostDTO {
    private long id;
    private String title;
    private String content;
    private String author;
}
