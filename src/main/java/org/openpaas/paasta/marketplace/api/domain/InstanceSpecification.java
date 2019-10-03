package org.openpaas.paasta.marketplace.api.domain;

import lombok.Data;
import org.openpaas.paasta.marketplace.api.domain.Instance.Status;
import org.openpaas.paasta.marketplace.api.domain.Software.Type;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class InstanceSpecification implements Specification<Instance> {

    private static final long serialVersionUID = 1L;
    
    private static String systemHost;

    private String host;

    private Yn inUse;

    private Status status;

    private String createdBy;

    private Long categoryId;

    private Long softwareId;

    private String softwareNameLike;

    private Type softwareType;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDateTime usageStartDate;

    @DateTimeFormat(iso = ISO.DATE)
    private LocalDateTime usageEndDate;

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
        if (softwareId != null) {
            restrictions.add(builder.equal(root.get("software").get("id"), softwareId));
        }
        if (softwareNameLike != null) {
            restrictions.add(builder.like(root.get("software").get("name"), "%" + softwareNameLike + "%"));
        }
        if (softwareType != null) {
            restrictions.add(builder.equal(root.get("software").get("type"), softwareType));
        }

        if (usageStartDate != null && usageEndDate != null) {
//            Predicate predicate = builder.or(
//                    builder.and(builder.lessThanOrEqualTo(root.get("usageStartDate"), usageStartDate),
//                            builder.or(builder.isNull(root.get("usageEndDate")),
//                                    builder.and(
//                                            builder.greaterThanOrEqualTo(root.get("usageEndDate"), usageStartDate),
//                                            builder.lessThan(root.get("usageEndDate"), usageEndDate)
//                                    )
//                            )
//                    ),
//                    builder.and(builder.greaterThanOrEqualTo(root.get("usageStartDate"), usageStartDate),
//                            builder.lessThan(root.get("usageStartDate"), usageEndDate)));

            Predicate predicate = builder.or(
                    builder.and(builder.lessThanOrEqualTo(root.get("usageStartDate"), usageStartDate),
                        builder.and(
                                builder.greaterThanOrEqualTo(builder.nullif(root.get("usageEndDate"), LocalDateTime.now()) , usageStartDate),
                                builder.lessThan(builder.nullif(root.get("usageEndDate"), LocalDateTime.now()), usageEndDate)
                        )
                    ),
                    builder.and(builder.greaterThanOrEqualTo(root.get("usageStartDate"), usageStartDate),
                            builder.lessThan(root.get("usageStartDate"), usageEndDate)));

            restrictions.add(predicate);
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
