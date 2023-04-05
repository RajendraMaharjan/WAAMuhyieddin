package edu.miu.waalab.repository;

import edu.miu.waalab.domain.Post;
import edu.miu.waalab.domain.PostV2;
import edu.miu.waalab.domain.dto.PostDTO;
import edu.miu.waalab.domain.value.SearchPost;
import edu.miu.waalab.domain.value.enums.SearchPostTypes;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    private ArrayList<Post> posts = new ArrayList<>(List.of(
            new Post(1, "WAA LAB 1 Discussion", "Lab 1 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new Post(2, "WAA LAB 2 Discussion", "Lab 2 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new Post(3, "WAA LAB 3 Discussion", "Lab 3 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new Post(4, "WAA LAB 4 Discussion", "Lab 4 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new Post(5, "WAA LAB 5 Discussion", "Lab 5 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new Post(6, "WAA LAB 6 Discussion", "Lab 6 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new Post(7, "One Piece Shonen Jump", "One author about a famous manga One piece", "One Piece"),
            new Post(8, "Algorithm", "Algorithm and Time Complexity of Algorithm", "Paul Corazza"),
            new Post(9, "DBMS", "Database Management System and Normalization", "Adrian Muzta"),
            new Post(10, "Enterprise Architecture", "Designing the architecture of the applications in enteprise world", "Rene De Jong"),
            new Post(11, "Software Architecture", "Designing the software architecture", "Saimak Tavakoli"))
    );

    private ArrayList<PostV2> postsV2 = new ArrayList<>(List.of(
            new PostV2(101, "WAA LAB 1 Discussion", "Lab 1 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new PostV2(201, "WAA LAB 2 Discussion", "Lab 2 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new PostV2(301, "WAA LAB 3 Discussion", "Lab 3 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new PostV2(401, "WAA LAB 4 Discussion", "Lab 4 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new PostV2(501, "WAA LAB 5 Discussion", "Lab 5 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new PostV2(601, "WAA LAB 6 Discussion", "Lab 6 Spring boot REST API Services", "Muhyieddin Al-Tarawneh"),
            new PostV2(701, "One Piece Shonen Jump", "One author about a famous manga One piece", "One Piece"),
            new PostV2(801, "Algorithm", "Algorithm and Time Complexity of Algorithm", "Paul Corazza"),
            new PostV2(901, "DBMS", "Database Management System and Normalization", "Adrian Muzta"),
            new PostV2(1001, "Enterprise Architecture", "Designing the architecture of the applications in enteprise world", "Rene De Jong"),
            new PostV2(1101, "Software Architecture", "Designing the software architecture", "Saimak Tavakoli"))
    );


    public List<Post> getAllPosts() {
        return posts;
    }


    public Optional<Post> getPost(Long id) {
        return posts.stream().filter(x -> x.getId() == id)
                .findFirst();
    }

    public Post savePost(Post post) {
        posts.add(post);
        return post;
    }

    public Post updatePost(Post main, Post updated) {
        main.setTitle(updated.getTitle());
        main.setAuthor(updated.getAuthor());
        main.setContent(updated.getContent());
        return main;
    }

    public boolean deletePost(Long id) {
        Optional<Post> post = posts.stream().filter(x -> x.getId() == id)
                .findFirst();

        if (post != null && post.isPresent()) {
            return posts.remove(post.get());
        } else {
            return false;
        }
    }

    public List<Post> searchPosts(String searchText) {
        return posts.stream()
                .filter(x -> (searchText != null && !searchText.isEmpty() && x.getAuthor().equals(searchText)))
                .collect(Collectors.toList());
    }

    public List<Post> searchingPosts(SearchPost searchPost) {

        Predicate<Post> filter = (post) -> {
            SearchPostTypes iniSearch = searchPost.getSearchPostTypes();
            String value = searchPost.getSearchContent();

            return switch (iniSearch) {
                case AUTHOR -> post.getAuthor().equals(value);
                case TITLE -> post.getTitle().equals(value);
                case CONTENT -> post.getContent().contains(value);
                default -> true;
            };
        };

        return posts.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public List<PostV2> searchingPostsV2(SearchPost searchPost) {

        Predicate<PostV2> filter = (post) -> {
            SearchPostTypes iniSearch = searchPost.getSearchPostTypes();
            String value = searchPost.getSearchContent();

            return switch (iniSearch) {
                case AUTHOR -> post.getAuthor().equals(value);
                case TITLE -> post.getTitle().equals(value);
                case CONTENT -> post.getContent().contains(value);
                default -> true;
            };
        };

        return postsV2.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

}
