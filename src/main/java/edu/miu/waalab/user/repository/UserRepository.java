package edu.miu.waalab.user.repository;

import edu.miu.waalab.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUserByPostsGreaterThan(Long id);

    @Query("select u from User u where SIZE(u.posts) > 1")
//    @Query("select u from User u JOIN u.posts p where SIZE(u.posts) > 1")
    List<User> findUsersHavingPostGrTnOne();
}
