package edu.miu.waalab.services.adapters;

import edu.miu.waalab.domain.Post;
import edu.miu.waalab.domain.dto.PostDTO;
import edu.miu.waalab.domain.dto.PostsDTO;
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
}
