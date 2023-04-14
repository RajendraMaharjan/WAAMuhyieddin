package edu.miu.waalab.service.user.impl;

import edu.miu.waalab.domain.comment.Comment;
import edu.miu.waalab.domain.post.Post;
import edu.miu.waalab.domain.post.dto.PostDTO;
import edu.miu.waalab.domain.post.dto.PostsDTO;
import edu.miu.waalab.domain.user.User;
import edu.miu.waalab.domain.user.dto.response.UserResponse;
import edu.miu.waalab.domain.user.dto.response.UsersDTO;
import edu.miu.waalab.domain.user.dto.request.UserDTO;
import edu.miu.waalab.repository.post.PostRepository;
import edu.miu.waalab.repository.user.UserRepository;
import edu.miu.waalab.service.comment.CommentService;
import edu.miu.waalab.service.post.adapters.DTOMapperAdapter;
import edu.miu.waalab.service.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private DTOMapperAdapter adapter;

    @Override
    public UsersDTO getAllUsers() {
        List<User> users = userRepository.findAll();
        return adapter.getUsersDTOFromUsers(users);
    }

    @Override
    public Optional<UserResponse> getUser(Long id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            return Optional.of(adapter.convertObject(oUser.get(), UserResponse.class));
        } else {
            return Optional.empty();
        }
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User user = adapter.getUserFromUserDTO(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return adapter.getUserDTOFromUser(userRepository.save(user));
    }

    @Override
    public PostsDTO getAllPostsFromUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return adapter.getPostsDTOFromPosts(optionalUser.get().getPosts());
        } else {
            return new PostsDTO(0, new ArrayList<>());
        }
    }

    @Override
    public UsersDTO getUsersHavingPostGrTnOne() {
        List<User> users = userRepository.findUsersHavingPostGrTnOne();
        return adapter.getUsersDTOFromUsers(users);
    }

    @Override
    public UsersDTO getUsersHavingPostGrTn(Long num) {
        List<User> users = userRepository.findUserByPostsGreaterThan(num);
        return adapter.getUsersDTOFromUsers(users);
    }

    @Override
    public Optional<PostDTO> savePostFromUser(Long userId, PostDTO postBody) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Post newPost = adapter.convertObject(postBody, Post.class);
            List<Post> posts = user.get().getPosts();
            posts.add(newPost);

            userRepository.save(user.get());

            Post lastUpdated = posts.stream().reduce((f, s) -> s).orElse(null);
            if (lastUpdated != null) {
                return Optional.of(adapter.convertObject(lastUpdated, PostDTO.class));
            }
        }
        return Optional.empty();
    }

    @Override
    public Boolean deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UsersDTO getAllUsersWithThePostTitled(String title) {
        List<User> users = userRepository.getAllUsersWithThePostTitled(title);
        return adapter.getUsersDTOFromUsers(users);
    }

    @Override
    public Optional<Comment> getCommentWithInAPostFromAUserWAYI(Long userId, Long postId, Long commentId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Optional<Post> post = postRepository.findById(postId);
            if (post.isPresent()) {
                Optional<Comment> comment = commentService.findComment(commentId);
                if (comment.isPresent()) {
                    return comment;
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Comment> getCommentWithInAPostFromAUser(Long userId, Long postId, Long commentId) {
        return userRepository.getCommentWithInAPostFromAUser(userId, postId, commentId);

    }

}
