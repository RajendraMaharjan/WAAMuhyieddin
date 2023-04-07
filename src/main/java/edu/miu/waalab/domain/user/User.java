package edu.miu.waalab.domain.user;

import edu.miu.waalab.domain.post.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
//    @Fetch(FetchMode.JOIN)
//    @BatchSize(size = 10)
    private List<Post> posts;

    public User(String name, List<Post> posts) {
        this.name = name;
        this.posts = posts;
    }
}
