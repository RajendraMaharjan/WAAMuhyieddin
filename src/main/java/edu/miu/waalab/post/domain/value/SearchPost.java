package edu.miu.waalab.post.domain.value;

import edu.miu.waalab.post.domain.value.enums.SearchPostTypes;
import lombok.Data;

@Data
public class SearchPost {
    private SearchPostTypes searchPostTypes;
    private String searchContent;
}