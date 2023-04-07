package edu.miu.waalab.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostsDTO {
    private int postCount;
    private List<PostDTO> posts;
}
