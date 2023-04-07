package edu.miu.waalab.domain.post.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private String message;
    private HttpStatus status;
}
