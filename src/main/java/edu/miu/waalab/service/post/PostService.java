package edu.miu.waalab.service.post;

import edu.miu.waalab.domain.post.dto.PostDTO;
import edu.miu.waalab.domain.post.dto.PostsDTO;
import edu.miu.waalab.domain.post.dto.PostsV2DTO;
import edu.miu.waalab.domain.post.value.SearchPost;

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

    PostsDTO findPostByGivenTitle(String title);
}
