package edu.miu.waalab.resource.comment;

import edu.miu.waalab.domain.comment.Comment;
import edu.miu.waalab.errors.customexception.ItemNotFoundException;
import edu.miu.waalab.service.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(path = "/{pId}/comments", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveCommentToPost(@PathVariable("pId") Long postId,
                                               @RequestBody Comment comment) throws ItemNotFoundException {
        Optional<Comment> oComment = commentService.saveCommentToPost(postId, comment);

        if (oComment.isPresent()) {
            return ResponseEntity.ok(oComment.get());
        } else {
            throw new ItemNotFoundException("Post your are trying to comment is unavailable.");
        }
    }

}
