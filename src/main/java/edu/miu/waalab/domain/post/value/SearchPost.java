package edu.miu.waalab.domain.post.value;

import edu.miu.waalab.domain.post.value.enums.SearchPostTypes;
import lombok.Data;

@Data
public class SearchPost {
    private SearchPostTypes searchPostTypes;
    private String searchContent;
}