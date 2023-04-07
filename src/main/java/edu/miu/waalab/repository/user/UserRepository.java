package edu.miu.waalab.repository.user;

import edu.miu.waalab.domain.comment.Comment;
import edu.miu.waalab.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where size(u.posts)>0 ")
    List<User> findUserByPostsGreaterThan(Long num);

    @Query("select u from User u where SIZE(u.posts) > 1")
//    @Query("select u from User u JOIN u.posts p where SIZE(u.posts) > 1")
    List<User> findUsersHavingPostGrTnOne();

    @Query("select u from User u JOIN u.posts p where p.title=:title")
    List<User> getAllUsersWithThePostTitled(@Param("title") String title);

    @Query("select c from User u JOIN u.posts p JOIN p.comments c where u.id=:userId AND p.id=:postId AND c.id=:commentId")
    Optional<Comment> getCommentWithInAPostFromAUser(@Param("userId") Long userId, @Param("postId") Long postId, @Param("commentId") Long commentId);
}
