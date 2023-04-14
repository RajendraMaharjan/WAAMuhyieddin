package edu.miu.waalab.domain.user;

import edu.miu.waalab.domain.post.Post;
import edu.miu.waalab.domain.auth.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String password;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
//    @Fetch(FetchMode.JOIN)
//    @BatchSize(size = 10)
    private List<Post> posts;

    public User(String firstname, String password, String email, Collection<Role> roles, List<Post> posts) {
        this.firstname = firstname;
        this.password = password;
        this.email = email;
        this.roles = roles;
        this.posts = posts;
    }
}
