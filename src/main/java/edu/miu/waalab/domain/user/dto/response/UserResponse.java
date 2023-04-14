package edu.miu.waalab.domain.user.dto.response;

import edu.miu.waalab.domain.auth.Role;
import edu.miu.waalab.domain.post.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String firstname;
    private String email;
}
