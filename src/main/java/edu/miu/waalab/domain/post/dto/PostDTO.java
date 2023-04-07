package edu.miu.waalab.domain.post.dto;

import edu.miu.waalab.domain.comment.Comment;
import lombok.Data;

import java.util.List;

@Data
public class PostDTO {
    private long id;
    private String title;
    private String content;
    private String author;

    //USE DTO later
    private List<Comment> comments;
}
