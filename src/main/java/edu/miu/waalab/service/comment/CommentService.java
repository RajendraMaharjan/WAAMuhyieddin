package edu.miu.waalab.service.comment;

import edu.miu.waalab.domain.comment.Comment;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> saveCommentToPost(Long postId, Comment comment);

    Optional<Comment> findComment(Long commentId);
}
