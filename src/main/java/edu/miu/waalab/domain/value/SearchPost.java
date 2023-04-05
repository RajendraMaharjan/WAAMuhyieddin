package edu.miu.waalab.domain.value;

import edu.miu.waalab.domain.value.enums.SearchPostTypes;
import lombok.Data;

@Data
public class SearchPost {
    private SearchPostTypes searchPostTypes;
    private String searchContent;
}