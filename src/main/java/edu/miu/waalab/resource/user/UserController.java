package edu.miu.waalab.resource.user;

import edu.miu.waalab.aspects.annotation.ExecutionTime;
import edu.miu.waalab.domain.comment.Comment;
import edu.miu.waalab.domain.post.dto.PostDTO;
import edu.miu.waalab.domain.post.value.ResponseMessage;
import edu.miu.waalab.errors.customexception.ItemNotFoundException;
import edu.miu.waalab.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.miu.waalab.domain.user.dto.UserDTO;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ExecutionTime
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) throws ItemNotFoundException {

        Optional<UserDTO> oUser = userService.getUser(id);

        if (oUser.isPresent()) {
            return ResponseEntity.ok(oUser.get());
        } else {
            throw new ItemNotFoundException("User not found.");
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

    @PostMapping(path = "/{uId}/posts", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> savePostFromUser(@PathVariable("uId") Long userId, @RequestBody PostDTO postBody) throws ItemNotFoundException {
        Optional<PostDTO> oPost = userService.savePostFromUser(userId, postBody);

        if (oPost.isPresent()) {
            return ResponseEntity.ok(oPost.get());
        } else {
            throw new ItemNotFoundException("Unable to post the message. User with " + userId + " is unavailable.");
        }
    }

    @GetMapping(path = "/postsgto", produces = "application/json")
    public ResponseEntity<?> getUsersHavingPostGrTnOne() {
        return ResponseEntity.ok(userService.getUsersHavingPostGrTnOne());
    }

    @GetMapping(path = "/postsgto/{number}", produces = "application/json")
    public ResponseEntity<?> getUsersHavingPostGrTnOne(@PathVariable("number") Long num) {
        return ResponseEntity.ok(userService.getUsersHavingPostGrTn(num));
    }

    @DeleteMapping(path = "/{uId}")
    public ResponseEntity<?> deleteUser(@PathVariable("uId") Long userId) throws ItemNotFoundException {
        Boolean deleted = userService.deleteUser(userId);

        if (deleted) {
            return ResponseEntity.ok(new ResponseMessage("User deleted successfully.", HttpStatus.OK));
        } else {
            throw new ItemNotFoundException("Unable to delete the user. User with " + userId + " is unavailable.");
        }
    }

    @GetMapping(path = "/posts/", produces = "application/json")
    public ResponseEntity<?> getAllUsersWithThePostTitled(@RequestParam() String title) {
        return ResponseEntity.ok(userService.getAllUsersWithThePostTitled(title));
    }

    @GetMapping(path = "/wayI/{userId}/posts/{postId}/comments/{commentId}", produces = "application/json")
    public ResponseEntity<?> getCommentWithInAPostFromAUserMultipleQuery(@PathVariable(required = true) Long userId,
                                                                         @PathVariable(required = true) Long postId,
                                                                         @PathVariable(required = true) Long commentId) throws ItemNotFoundException {

        Optional<Comment> oComment = userService.getCommentWithInAPostFromAUserWAYI(userId, postId, commentId);

        if (oComment.isPresent()) {
            return ResponseEntity.ok(oComment.get());
        } else {
            throw new ItemNotFoundException("Comment not found.");
        }
    }

    @GetMapping(path = "/{userId}/posts/{postId}/comments/{commentId}", produces = "application/json")
    public ResponseEntity<?> getCommentWithInAPostFromAUser(@PathVariable(required = true) Long userId,
                                                            @PathVariable(required = true) Long postId,
                                                            @PathVariable(required = true) Long commentId) throws ItemNotFoundException {

        Optional<Comment> oComment = userService.getCommentWithInAPostFromAUser(userId, postId, commentId);

        if (oComment.isPresent()) {
            return ResponseEntity.ok(oComment.get());
        } else {
            throw new ItemNotFoundException("Comment not found.");
        }
    }

}
