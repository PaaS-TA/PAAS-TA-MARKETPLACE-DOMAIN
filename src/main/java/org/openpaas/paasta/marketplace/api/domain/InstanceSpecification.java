package org.openpaas.paasta.marketplace.api.domain;

import java.time.LocalDateTime;
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
    
    private static String systemHost;

    private String host;

    private Yn inUse;

    private Status status;

    private String createdBy;

    private Long categoryId;

    private String softwareNameLike;

    private Type softwareType;

    private Instance.ProvisionStatus provisionStatus;

    private List<Instance.ProvisionStatus> provisionStatusIn = new ArrayList<>();

    private Integer provisionTryCountMax;

    private LocalDateTime provisionStartDateBefore;

    private Instance.ProvisionStatus deprovisionStatus;

    private List<Instance.ProvisionStatus> deprovisionStatusIn = new ArrayList<>();

    private Integer deprovisionTryCountMax;

    private LocalDateTime deprovisionStartDateBefore;

    public static void setSystemHost(String systemHost) {
        InstanceSpecification.systemHost = systemHost;
    }

    public static InstanceSpecification ofSystemHost() {
        InstanceSpecification spec = new InstanceSpecification();
        spec.setHost(systemHost);

        return spec;
    }
    
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
        if (provisionStatus != null) {
            restrictions.add(builder.equal(root.get("provisionStatus"), provisionStatus));
        }
        if (provisionStatusIn != null && !provisionStatusIn.isEmpty()) {
            restrictions.add(root.get("provisionStatus").in(provisionStatusIn));
        }
        if (provisionTryCountMax != null) {
            restrictions.add(builder.lessThanOrEqualTo(root.get("provisionTryCount"), provisionTryCountMax));
        }
        if (provisionStartDateBefore != null) {
            restrictions.add(builder.lessThan(root.get("provisionStartDate"), provisionStartDateBefore));
        }
        if (deprovisionStatus != null) {
            restrictions.add(builder.equal(root.get("deprovisionStatus"), deprovisionStatus));
        }
        if (deprovisionStatusIn != null && !deprovisionStatusIn.isEmpty()) {
            restrictions.add(root.get("deprovisionStatus").in(deprovisionStatusIn));
        }
        if (deprovisionTryCountMax != null) {
            restrictions.add(builder.lessThanOrEqualTo(root.get("deprovisionTryCount"), deprovisionTryCountMax));
        }
        if (deprovisionStartDateBefore != null) {
            restrictions.add(builder.lessThan(root.get("deprovisionStartDate"), deprovisionStartDateBefore));
        }
        if (host != null) {
            restrictions.add(builder.equal(root.get("host"), host));
        }

        return builder.and(restrictions.toArray(new Predicate[] {}));
    }

}