package org.openpaas.paasta.marketplace.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.openpaas.paasta.marketplace.api.domain.Instance.Status;
import org.openpaas.paasta.marketplace.api.domain.Software.Type;
import org.springframework.data.jpa.domain.Specification;

import lombok.Data;

@Data
public class InstanceSpecification implements Specification<Instance> {

    private static final long serialVersionUID = 1L;

    private Yn inUse;

    private Status status;

    private String createdBy;

    private Long categoryId;

    private String softwareNameLike;

    private Type softwareType;

    @Override
    public Predicate toPredicate(Root<Instance> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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
            restrictions.add(builder.equal(root.get("software").get("category").get("id"), categoryId));
        }
        if (softwareNameLike != null) {
            restrictions.add(builder.like(root.get("software").get("name"), "%" + softwareNameLike + "%"));
        }
        if (softwareType != null) {
            restrictions.add(builder.equal(root.get("software").get("type"), softwareType));
        }

        return builder.and(restrictions.toArray(new Predicate[] {}));
    }

}
