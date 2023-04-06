package edu.miu.waalab.post.repository;

import edu.miu.waalab.post.domain.Post;
import edu.miu.waalab.post.domain.PostV2;
import edu.miu.waalab.post.domain.value.SearchPost;
import edu.miu.waalab.post.domain.value.enums.SearchPostTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
