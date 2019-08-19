package org.openpaas.paasta.marketplace.api.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class ProfileSpecification implements Specification<Profile> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String idLike;

    private String nameLike;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime createdDateAfter;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private LocalDateTime createdDateBefore;

    @Override
    public Predicate toPredicate(Root<Profile> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> restrictions = new ArrayList<>();
        if (id != null) {
            restrictions.add(builder.equal(root.get("id"), id));
        }
        if (idLike != null) {
            restrictions.add(builder.like(root.get("id"), "%" + idLike + "%"));
        }
        if (nameLike != null) {
            restrictions.add(builder.like(root.get("name"), "%" + nameLike + "%"));
        }
        if (createdDateAfter != null) {
            restrictions.add(builder.greaterThanOrEqualTo(root.get("createdDate"), createdDateAfter));
        }
        if (createdDateBefore != null) {
            restrictions.add(builder.lessThan(root.get("createdDate"), createdDateBefore));
        }

        return builder.and(restrictions.toArray(new Predicate[] {}));
    }

}
