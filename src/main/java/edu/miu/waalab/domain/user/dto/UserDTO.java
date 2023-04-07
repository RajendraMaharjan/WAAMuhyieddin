package edu.miu.waalab.domain.user.dto;

import edu.miu.waalab.domain.post.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private List<PostDTO> posts;
}
