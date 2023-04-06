package edu.miu.waalab.user.services.impl;

import edu.miu.waalab.post.domain.Post;
import edu.miu.waalab.post.domain.dto.PostDTO;
import edu.miu.waalab.post.domain.dto.PostsDTO;
import edu.miu.waalab.post.services.adapters.DTOMapperAdapter;
import edu.miu.waalab.user.domain.User;
import edu.miu.waalab.user.domain.dto.UserDTO;
import edu.miu.waalab.user.domain.dto.UsersDTO;
import edu.miu.waalab.user.repository.UserRepository;
import edu.miu.waalab.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DTOMapperAdapter adapter;

    @Override
    public UsersDTO getAllUsers() {
        List<User> users = userRepository.findAll();
        return adapter.getUsersDTOFromUsers(users);
    }

    @Override
    public Optional<UserDTO> getUser(Long id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            return Optional.of(adapter.convertObject(oUser.get(), UserDTO.class));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        return adapter.getUserDTOFromUser(userRepository.save(adapter.getUserFromUserDTO(userDTO)));
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

}
