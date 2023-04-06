package edu.miu.waalab.user.services;

import edu.miu.waalab.post.domain.dto.PostsDTO;
import edu.miu.waalab.user.domain.dto.UserDTO;
import edu.miu.waalab.user.domain.dto.UsersDTO;

import java.util.Optional;

public interface UserService {

    UsersDTO getAllUsers();

    Optional<UserDTO> getUser(Long id);

    UserDTO saveUser(UserDTO userDTO);

    PostsDTO getAllPostsFromUser(Long id);

    UsersDTO getUsersHavingPostGrTnOne();
}
