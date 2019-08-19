package org.openpaas.paasta.marketplace.api.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.openpaas.paasta.marketplace.api.domain.Software.Status;
import org.openpaas.paasta.marketplace.api.domain.Software.Type;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class SoftwareSpecification implements Specification<Software> {

    private static final long serialVersionUID = 1L;

    private Yn inUse;

    private Status status;

    private String createdBy;

    private Long categoryId;

    private String nameLike;

    private Type type;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime createdDateAfter;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime createdDateBefore;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime statusModifiedDateAfter;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime statusModifiedDateBefore;

    @Override
    public Predicate toPredicate(Root<Software> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> restrictions = new ArrayList<>();
        if (inUse != null) {
            restrictions.add(builder.equal(root.get("inUse"), inUse));
        }
        if (status != null) {
            restrictions.add(builder.equal(root.get("status"), status));
        }
        if (createdBy != null) {
            restrictions.add(builder.equal(root.get("createdBy"), createdBy));
        }
        if (categoryId != null) {
            restrictions.add(builder.equal(root.get("category").get("id"), categoryId));
        }
        if (nameLike != null) {
            restrictions.add(builder.like(root.get("name"), "%" + nameLike + "%"));
        }
        if (type != null) {
            restrictions.add(builder.equal(root.get("type"), type));
        }
        if (createdDateAfter != null) {
            restrictions.add(builder.greaterThanOrEqualTo(root.get("createdDate"), createdDateAfter));
        }
        if (createdDateBefore != null) {
            restrictions.add(builder.lessThan(root.get("createdDate"), createdDateBefore));
        }
        if (statusModifiedDateAfter != null) {
            restrictions.add(builder.greaterThanOrEqualTo(root.get("statusModifiedDate"), statusModifiedDateAfter));
        }
        if (statusModifiedDateBefore != null) {
            restrictions.add(builder.lessThan(root.get("statusModifiedDate"), statusModifiedDateBefore));
        }

        return builder.and(restrictions.toArray(new Predicate[] {}));
    }

}
