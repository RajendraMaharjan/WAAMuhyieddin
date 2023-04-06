package edu.miu.waalab.post.services.impl;

import edu.miu.waalab.post.domain.Post;
import edu.miu.waalab.post.domain.dto.PostDTO;
import edu.miu.waalab.post.domain.dto.PostsDTO;
import edu.miu.waalab.post.domain.dto.PostsV2DTO;
import edu.miu.waalab.post.domain.value.SearchPost;
import edu.miu.waalab.post.services.adapters.DTOMapperAdapter;
import edu.miu.waalab.post.repository.PostRepository;
import edu.miu.waalab.post.services.PostService;
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
        PostsDTO postsDTO = mapperAdapter.getPostsDTOFromPosts(postRepository.findAll());
        return postsDTO;
    }

    @Override
    public PostsDTO searchPost(String searchText) {
//        PostsDTO postsDTO = mapperAdapter.getPostsDTOFromPosts(postRepository.findAll(searchText));
//        return postsDTO;
        return null;
    }

    @Override
    public PostsDTO searchingPosts(SearchPost searchPost) {
//        PostsDTO postsDTO = mapperAdapter.getPostsDTOFromPosts(postRepository.searchingPosts(searchPost));
//        return postsDTO;
        return null;
    }

    @Override
    public Optional<PostDTO> getPost(Long id) {
        Optional<Post> oPost = postRepository.findById(id);
        if (oPost.isPresent()) {
            return Optional.of(mapperAdapter.convertObject(postRepository.findById(id).get()
                    , PostDTO.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public PostDTO savePost(PostDTO postDTO) {
        postRepository.save(mapperAdapter.convertObject(postDTO, Post.class));
        return postDTO;
    }

    @Override
    public PostsV2DTO searchingPostV2s(SearchPost searchPost) {
//        PostsV2DTO postsV2DTO = mapperAdapter.getPostsV2DTOFromPostsV2(postRepository.searchingPostsV2(searchPost));
//        return postsV2DTO;
        return null;
    }

    @Override
    public boolean deletePost(Long id) {
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
        Optional<Post> oPost = postRepository.findById(postDTO.getId());
        if (oPost.isPresent()) {
            Post post = postRepository.save(mapperAdapter.convertObject(postDTO, Post.class));
            return Optional.of(mapperAdapter.convertObject(post, PostDTO.class));
        } else {
            return Optional.empty();
        }
    }

}
