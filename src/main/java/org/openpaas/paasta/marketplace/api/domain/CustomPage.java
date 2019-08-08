package org.openpaas.paasta.marketplace.api.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomPage<T> extends PageImpl<T> {

    private static final long serialVersionUID = 1L;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomPage(
        // @formatter:off
            @JsonProperty("content") List<T> content, 
            @JsonProperty("number") int number, 
            @JsonProperty("size") int size, 
            @JsonProperty("totalElements") Long totalElements, 
            // FIXME: data loss
            @JsonProperty("pageable") HashMap<?, ?> pageable, 
            @JsonProperty("totalPages") int totalPages, 
            // FIXME: data loss
            @JsonProperty("sort") HashMap<?, ?> sort, 
            @JsonProperty("numberOfElements") int numberOfElements) {
        // @formatter:on
        super(content, PageRequest.of(number, size), totalElements);
    }

    public CustomPage() {
        super(new ArrayList<>());
    }

    @Deprecated
    public Page<T> toPage() {
        return this;
    }

}
