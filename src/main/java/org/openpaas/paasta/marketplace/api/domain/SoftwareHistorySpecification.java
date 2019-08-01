package org.openpaas.paasta.marketplace.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import lombok.Data;

@Data
public class SoftwareHistorySpecification implements Specification<SoftwareHistory> {

    private static final long serialVersionUID = 1L;

    private Long softwareId;

    @Override
    public Predicate toPredicate(Root<SoftwareHistory> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> restrictions = new ArrayList<>();
        if (softwareId != null) {
            restrictions.add(builder.equal(root.get("software").get("id"), softwareId));
        }

        return builder.and(restrictions.toArray(new Predicate[] {}));
    }

}
