package edu.miu.waalab.domain.user.dto.response;

import edu.miu.waalab.domain.user.dto.request.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsersDTO {
    private int userCount;
    private List<UserDTO> users;
}