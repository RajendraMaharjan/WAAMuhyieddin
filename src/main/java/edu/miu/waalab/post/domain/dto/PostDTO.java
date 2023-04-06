package edu.miu.waalab.post.domain.dto;

import lombok.Data;

@Data
public class PostDTO {
    private long id;
    private String title;
    private String content;
    private String author;
}
