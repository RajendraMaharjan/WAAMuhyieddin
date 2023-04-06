package edu.miu.waalab.post.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostsV2DTO {
    private int postCount;
    private List<PostV2DTO> posts;
}
