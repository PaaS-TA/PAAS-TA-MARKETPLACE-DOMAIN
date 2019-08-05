package org.openpaas.paasta.marketplace.api.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomPage<T> {

    private int number;

    private int size;

    private int totalPages;

    private int numberOfElements;

    private long totalElements;

    private boolean previousPage;

    private boolean firstPage;

    private boolean nextPage;

    private boolean lastPage;

    private List<T> content;

    public CustomPage() {
    }

    public Page<T> toPage() {
        return new PageImpl<>(getContent(), PageRequest.of(getNumber(), getSize()), getTotalElements());
    }

}
