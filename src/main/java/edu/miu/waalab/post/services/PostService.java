package edu.miu.waalab.post.services;

import edu.miu.waalab.post.domain.dto.PostDTO;
import edu.miu.waalab.post.domain.dto.PostsDTO;
import edu.miu.waalab.post.domain.dto.PostsV2DTO;
import edu.miu.waalab.post.domain.value.SearchPost;

import java.util.Optional;

public interface PostService {

    PostsDTO getAllPosts();

    PostsDTO searchPost(String searchText);

    PostsDTO searchingPosts(SearchPost searchPost);

    Optional<PostDTO> getPost(Long id);

    boolean deletePost(Long id);

    Optional<PostDTO> updatePost(PostDTO postDTO);

    PostDTO savePost(PostDTO postDTO);

    PostsV2DTO searchingPostV2s(SearchPost searchPost);
}
