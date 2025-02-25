package org.project.stockservice.dto.paged;

import lombok.Builder;

import java.util.List;

@Builder
public record PagedResult<T> (
        List<T> data,
        long totalElements,
        int pageNumber,
        int totalPages,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious
) {}
