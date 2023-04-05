package edu.miu.waalab.services;

import edu.miu.waalab.domain.dto.PostDTO;
import edu.miu.waalab.domain.dto.PostsDTO;
import edu.miu.waalab.domain.value.SearchPost;

import java.util.Optional;

public interface PostService {

    PostsDTO getAllPosts();

    PostsDTO searchPost(String searchText);

    PostsDTO searchingPosts(SearchPost searchPost);

    Optional<PostDTO> getPost(Long id);

    boolean deletePost(Long id);

    Optional<PostDTO> updatePost(PostDTO postDTO);

    PostDTO savePost(PostDTO postDTO);

}
