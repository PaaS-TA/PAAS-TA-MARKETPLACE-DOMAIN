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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class InstanceCartSpecification implements Specification<InstanceCart> {

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
    
    private List<Long> inInstanceCartId;
 
    public static void setSystemHost(String systemHost) {
        InstanceCartSpecification.systemHost = systemHost;
    }

    public static InstanceCartSpecification ofSystemHost() {
        InstanceCartSpecification spec = new InstanceCartSpecification();
        spec.setHost(systemHost);
        return spec;
    }
    
    @Override
    public Predicate toPredicate(Root<InstanceCart> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> restrictions = new ArrayList<>();
        
        if (createdBy != null) {
            restrictions.add(builder.equal(root.get("createdBy"), createdBy));
        }
        if (inInstanceCartId != null && !inInstanceCartId.isEmpty()) {
            restrictions.add(root.get("id").in(inInstanceCartId));
        }

        return builder.and(restrictions.toArray(new Predicate[]{}));
    }
}
