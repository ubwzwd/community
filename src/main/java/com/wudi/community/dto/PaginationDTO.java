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
    private Integer totalPage;
    // the list of pages shown in the bar
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {

        totalPage = (totalCount + size - 1) / size;
        if(page < 1) page = 1;
        else if(page > totalPage) page = totalPage;
        this.page = page;

        pages.add(page);
        for(int i = 1; i < 3; i++){
            if(page-i > 0){
                pages.add(0, page-i);
            }
            if(page+i <= totalPage){
                pages.add(page+i);
            }
        }

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
        if(pages.contains((totalPage))){
            showLastPage = false;
        } else{
            showLastPage = true;
        }

    }
}
