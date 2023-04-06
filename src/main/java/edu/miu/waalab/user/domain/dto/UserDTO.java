package edu.miu.waalab.user.domain.dto;

import edu.miu.waalab.post.domain.Post;
import edu.miu.waalab.post.domain.dto.PostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private List<PostDTO> posts;
}
