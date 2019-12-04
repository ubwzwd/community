package com.wudi.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<PostDTO> posts;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;
    // the current page number
    private Integer page;
    // the list of pages shown in the bar
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage = (int)Math.ceil(totalCount / size);

        // weather show the previous or next page
        if(page == 1){
            showPrevious = false;
        }
        else{
            showPrevious = true;
        }
        if(page == totalPage){
            showNext = false;
        }
        else{
            showNext = true;
        }

        // if show first or last page
        if(pages.contains(1)){
            showFirstPage = false;
        } else{
            showFirstPage = true;
        }
    }
}
