package edu.miu.waalab.resources;

import edu.miu.waalab.domain.dto.PostDTO;
import edu.miu.waalab.domain.value.ResponseMessage;
import edu.miu.waalab.domain.value.SearchPost;
import edu.miu.waalab.domain.value.enums.SearchPostTypes;
import edu.miu.waalab.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<?> getAllPosts(@RequestParam(required = false) String author) {

        if (author != null && !author.isEmpty()) {
            return ResponseEntity.ok(postService.searchPost(author));
        } else {
            return ResponseEntity.ok(postService.getAllPosts());
        }
    }


    /**
     * Searching all posts done performed on body {@link SearchPost} with the {@link SearchPostTypes}
     * as mentioned by the consumer end
     *
     * @param searchPost {@link SearchPost} Object for searching posts specified by the user
     * @return returns {@link ResponseEntity} of post results based on the search done
     */
    @PostMapping(path = "/search", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> searchPosts(@RequestBody SearchPost searchPost) {

        return ResponseEntity.ok(postService.searchingPosts(searchPost));

//        SearchPostTypes iniSearch = searchPost.getSearchPostTypes();
//        String value = searchPost.getSearchContent();
//
//        return switch (iniSearch) {
//            case AUTHOR -> ResponseEntity.ok(postService.searchPost(value));
//            case TITLE -> ResponseEntity.ok(postService.searchPost(value));
//            case CONTENT -> ResponseEntity.ok(postService.searchPost(value));
//            default -> ResponseEntity.ok(postService.searchPost(value));
//        };
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") Long id) {

        Optional<PostDTO> postDTO = postService.getPost(id);
        if (postDTO != null && postDTO.isPresent()) {
            return ResponseEntity.ok(postDTO.get());
        } else {
            return new ResponseEntity<>(new ResponseMessage("Post not not found in the data resources", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> savePost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.savePost(postDTO));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
        boolean result = postService.deletePost(id);

        return result ? new ResponseEntity<>(new ResponseMessage("Item Deleted: " + postService.deletePost(id), HttpStatus.OK), HttpStatus.OK)
                : new ResponseEntity<>(new ResponseMessage("Item NOT FOUND", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO) {
        Optional<PostDTO> updated = postService.updatePost(postDTO);
        if (updated.isPresent()) {
            return ResponseEntity.ok(postDTO);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Post not not found in the data resources", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }
}
