package org.openpaas.paasta.marketplace.api.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class InstanceCart extends AbstractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private Software software;
    
    private String softwarePlanId;

    private String appName;

    private String appGuid;

    private String appUrl;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private ProvisionStatus provisionStatus;

    private Integer provisionTryCount = 0;

    private LocalDateTime provisionStartDate;

    private LocalDateTime provisionEndDate;

    @Enumerated(EnumType.STRING)
    private ProvisionStatus deprovisionStatus;

    private Integer deprovisionTryCount = 0;

    private LocalDateTime deprovisionStartDate;

    private LocalDateTime deprovisionEndDate;

    private LocalDateTime lastStatusCheckDate;

    private LocalDateTime usageStartDate;

    private LocalDateTime usageEndDate;

    private String host;
    
    @Transient
    private Long SoftwarePlanAmtMonth;
    
    public enum Status {
        // provision
        Approval,
        // deprovision
        Deleted
    }

    public enum ProvisionStatus {
        Pending, Ready, InProgress, Successful, Failed
    }
    
    @PrePersist
    @Override
    public void prePersist() {}
}