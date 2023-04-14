package edu.miu.waalab.service.post.adapters;

import edu.miu.waalab.domain.post.Post;
import edu.miu.waalab.domain.post.PostV2;
import edu.miu.waalab.domain.post.dto.PostDTO;
import edu.miu.waalab.domain.post.dto.PostV2DTO;
import edu.miu.waalab.domain.post.dto.PostsDTO;
import edu.miu.waalab.domain.post.dto.PostsV2DTO;
import edu.miu.waalab.domain.user.User;
import edu.miu.waalab.domain.user.dto.request.UserDTO;
import edu.miu.waalab.domain.user.dto.response.UsersDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOMapperAdapter {

    @Autowired
    private ModelMapper modelMapper;

    public <T, U> U convertObject(T object, Class<U> targetClass) {
        return modelMapper.map(object, targetClass);
    }

    public PostsDTO getPostsDTOFromPosts(List<Post> allPosts) {
        List<PostDTO> dtos = allPosts.stream().map(x -> modelMapper.map(x, PostDTO.class)).collect(Collectors.toList());
        return new PostsDTO(dtos.size(), dtos);
    }

    public PostsV2DTO getPostsV2DTOFromPostsV2(List<PostV2> allPosts) {
        List<PostV2DTO> dtos = allPosts.stream().map(x -> modelMapper.map(x, PostV2DTO.class)).collect(Collectors.toList());
        return new PostsV2DTO(dtos.size(), dtos);
    }


    //    Make generic if it's possible later
    public UsersDTO getUsersDTOFromUsers(List<User> allUsers) {
        List<UserDTO> dtos = allUsers.stream().map(user -> {

            List<PostDTO> postDTOList = user.getPosts().stream().map(pos -> convertObject(pos, PostDTO.class)).collect(Collectors.toList());

            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setFirstname(user.getFirstname());
            userDTO.setPosts(postDTOList);
            return userDTO;
        }).collect(Collectors.toList());

        return new UsersDTO(dtos.size(), dtos);
    }

    public User getUserFromUserDTO(UserDTO ud) {
        return new User(ud.getId(), ud.getFirstname(), ud.getPassword(), ud.getEmail(), ud.getRoles(), ud.getPosts() != null ? ud.getPosts().stream()
                .map(postDTO -> {
                    Post p = new Post(postDTO.getTitle(), postDTO.getContent(), postDTO.getAuthor(), postDTO.getComments());
                    return p;
                }).collect(Collectors.toList()) : null);
    }

    public UserDTO getUserDTOFromUser(User u) {
        List<PostDTO> pds = u.getPosts() != null ? u.getPosts().stream().map(p -> convertObject(p, PostDTO.class)).collect(Collectors.toList()) : new ArrayList<>();
        return new UserDTO(u.getId(), u.getFirstname(), u.getPassword(), u.getEmail(), pds, u.getRoles());
    }
}
