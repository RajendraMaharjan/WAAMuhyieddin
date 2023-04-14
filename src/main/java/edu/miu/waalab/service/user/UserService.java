package edu.miu.waalab.service.user;

import edu.miu.waalab.domain.comment.Comment;
import edu.miu.waalab.domain.post.dto.PostDTO;
import edu.miu.waalab.domain.post.dto.PostsDTO;
import edu.miu.waalab.domain.user.dto.request.UserDTO;
import edu.miu.waalab.domain.user.dto.response.UserResponse;
import edu.miu.waalab.domain.user.dto.response.UsersDTO;

import java.util.Optional;

public interface UserService {

    UsersDTO getAllUsers();

    Optional<UserResponse> getUser(Long id);

    UserDTO saveUser(UserDTO userDTO);

    PostsDTO getAllPostsFromUser(Long id);

    UsersDTO getUsersHavingPostGrTnOne();

    UsersDTO getUsersHavingPostGrTn(Long num);

    Optional<PostDTO> savePostFromUser(Long userId, PostDTO postBody);

    Boolean deleteUser(Long userId);

    UsersDTO getAllUsersWithThePostTitled(String title);

    Optional<Comment> getCommentWithInAPostFromAUserWAYI(Long userId, Long postId, Long commentId);

    Optional<Comment> getCommentWithInAPostFromAUser(Long userId, Long postId, Long commentId);
}
