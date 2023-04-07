package edu.miu.waalab.domain.comment;

import edu.miu.waalab.domain.post.Post;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    Post post;

}
