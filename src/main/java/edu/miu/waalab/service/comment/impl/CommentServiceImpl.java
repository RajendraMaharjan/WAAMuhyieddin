package edu.miu.waalab.service.comment.impl;

import edu.miu.waalab.domain.comment.Comment;
import edu.miu.waalab.domain.post.Post;
import edu.miu.waalab.domain.post.dto.PostDTO;
import edu.miu.waalab.repository.comment.CommentRepository;
import edu.miu.waalab.repository.post.PostRepository;
import edu.miu.waalab.service.comment.CommentService;
import edu.miu.waalab.service.post.PostService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostService postService;


    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Comment> saveCommentToPost(Long postId, Comment comment) {

        Optional<PostDTO> oPost = postService.getPost(postId);

        if (oPost.isPresent()) {
            List<Comment> comments = oPost.get().getComments();
            comments.add(comment);

            postService.savePost(oPost.get()); // THIS EXTRA line

            Comment lastUpdated = comments.stream().reduce((f, s) -> s).orElse(null);
            if (lastUpdated != null) {
                return Optional.of(lastUpdated);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Comment> findComment(Long commentId) {
        return commentRepository.findById(commentId);
    }
}
