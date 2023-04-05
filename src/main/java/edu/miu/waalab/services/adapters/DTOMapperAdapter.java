package edu.miu.waalab.services.adapters;

import edu.miu.waalab.domain.Post;
import edu.miu.waalab.domain.PostV2;
import edu.miu.waalab.domain.dto.PostDTO;
import edu.miu.waalab.domain.dto.PostV2DTO;
import edu.miu.waalab.domain.dto.PostsDTO;
import edu.miu.waalab.domain.dto.PostsV2DTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOMapperAdapter {

    @Autowired
    ModelMapper modelMapper;

    public PostDTO getPostDTOFromPost(Post post) {
        return modelMapper.map(post, PostDTO.class);
    }

    public Post getPostFromPostDTO(PostDTO postDTO) {
        return modelMapper.map(postDTO, Post.class);
    }

    public PostsDTO getPostsDTOFromPosts(List<Post> allPosts) {
        List<PostDTO> dtos = allPosts.stream()
                .map(x -> modelMapper.map(x, PostDTO.class))
                .collect(Collectors.toList());
        return new PostsDTO(dtos.size(), dtos);
    }

    public PostsV2DTO getPostsV2DTOFromPostsV2(List<PostV2> allPosts) {
        List<PostV2DTO> dtos = allPosts.stream()
                .map(x -> modelMapper.map(x, PostV2DTO.class))
                .collect(Collectors.toList());
        return new PostsV2DTO(dtos.size(), dtos);
    }
}
