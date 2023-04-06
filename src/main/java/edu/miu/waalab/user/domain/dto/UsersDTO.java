package edu.miu.waalab.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsersDTO {
    private int userCount;
    private List<UserDTO> users;
}