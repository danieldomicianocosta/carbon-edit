package com.carbon.refactor.application.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic DTO for paginated responses.
 * 
 * @param <T> The type of content in the page
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> {
    
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
}
