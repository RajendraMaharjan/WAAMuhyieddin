package edu.miu.waalab.user.resources;

import edu.miu.waalab.post.domain.value.ResponseMessage;
import edu.miu.waalab.user.domain.dto.UserDTO;
import edu.miu.waalab.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

        Optional<UserDTO> oUser = userService.getUser(id);

        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get());
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage("User not Found."
                    , HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @GetMapping(path = "/{id}/posts", produces = "application/json")
    public ResponseEntity<?> getAllPostsFromUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getAllPostsFromUser(id));
    }

    @GetMapping(path = "/postsgto", produces = "application/json")
    public ResponseEntity<?> getUsersHavingPostGrTnOne() {
        return ResponseEntity.ok(userService.getUsersHavingPostGrTnOne());
    }
}
