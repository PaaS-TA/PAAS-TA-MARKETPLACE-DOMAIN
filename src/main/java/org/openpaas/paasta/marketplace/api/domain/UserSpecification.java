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
public class UserSpecification implements Specification<User> {

    private static final long serialVersionUID = 1L;

    private String idLike;

    private String nameLike;

    private String idOrNameLike;

    private User.Role role;

    private List<String> idIn;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> restrictions = new ArrayList<>();
        if (idLike != null) {
            restrictions.add(builder.like(root.get("id"), "%" + idLike + "%"));
        }
        if (nameLike != null) {
            restrictions.add(builder.like(root.get("name"), "%" + nameLike + "%"));
        }
        if (idOrNameLike != null) {
            Predicate idLike = builder.like(root.get("id"), "%" + idOrNameLike + "%");
            Predicate nameLike = builder.like(root.get("name"), "%" + idOrNameLike + "%");
            restrictions.add(builder.or(idLike, nameLike));
        }
        if (role != null) {
            restrictions.add(builder.equal(root.get("role"), role));
        }
        if (idIn != null && !idIn.isEmpty()) {
            restrictions.add(root.get("id").in(idIn));
        }

        return builder.and(restrictions.toArray(new Predicate[] {}));
    }

}
