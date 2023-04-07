package edu.miu.waalab.domain.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostV2 {
    private long id;
    private String title;
    private String content;
    private String author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostV2 postV2 = (PostV2) o;
        return id == postV2.id && Objects.equals(title, postV2.title) && Objects.equals(content, postV2.content) && Objects.equals(author, postV2.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, author);
    }
}
