package edu.miu.waalab.repository.post;

import edu.miu.waalab.domain.post.Post;
import edu.miu.waalab.domain.post.value.SearchPost;
import edu.miu.waalab.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.title=:title")
    List<Post> findPostByGivenTitle(@Param("title") String title);
}
