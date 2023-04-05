package edu.miu.waalab.services.impl;

import edu.miu.waalab.domain.Post;
import edu.miu.waalab.domain.dto.PostDTO;
import edu.miu.waalab.domain.dto.PostsDTO;
import edu.miu.waalab.domain.value.SearchPost;
import edu.miu.waalab.domain.value.enums.SearchPostTypes;
import edu.miu.waalab.repository.PostRepository;
import edu.miu.waalab.services.PostService;
import edu.miu.waalab.services.adapters.DTOMapperAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    DTOMapperAdapter mapperAdapter;

    @Override
    public PostsDTO getAllPosts() {
        PostsDTO postsDTO = mapperAdapter.getPostsDTOFromPosts(postRepository.getAllPosts());
        return postsDTO;
    }

    @Override
    public PostsDTO searchPost(String searchText) {
        PostsDTO postsDTO = mapperAdapter.getPostsDTOFromPosts(postRepository.searchPosts(searchText));
        return postsDTO;
    }

    @Override
    public PostsDTO searchingPosts(SearchPost searchPost) {
        PostsDTO postsDTO = mapperAdapter.getPostsDTOFromPosts(postRepository.searchingPosts(searchPost));
        return postsDTO;
    }

    @Override
    public Optional<PostDTO> getPost(Long id) {
        Optional<Post> oPost = postRepository.getPost(id);
        if (oPost.isPresent()) {
            return Optional.of(mapperAdapter.getPostDTOFromPost(postRepository.getPost(id).get()));
        } else {
            return null;
        }
    }

    @Override
    public PostDTO savePost(PostDTO postDTO) {
        postRepository.savePost(mapperAdapter.getPostFromPostDTO(postDTO));
        return postDTO;
    }

    @Override
    public boolean deletePost(Long id) {
        return postRepository.deletePost(id);
    }

    @Override
    public Optional<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
        Optional<Post> oPost = postRepository.getPost(postDTO.getId());
        if (oPost.isPresent()) {
            Post post = postRepository.updatePost(oPost.get(), mapperAdapter.getPostFromPostDTO(postDTO));
            return Optional.of(mapperAdapter.getPostDTOFromPost(post));
        } else {
            return null;
        }
    }

}
