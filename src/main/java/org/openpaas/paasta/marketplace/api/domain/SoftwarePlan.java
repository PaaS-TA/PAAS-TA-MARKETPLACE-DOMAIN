package org.openpaas.paasta.marketplace.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class SoftwarePlan extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private Software software;

    private String applyMonth;	//yyyyMM

    private String name;

    private String description;
    
    private String memorySize;

    private String diskSize;
    
    private Integer cpuAmt = 0;		//원/월

    private Integer memoryAmt = 0;	//원/월

    private Integer diskAmt = 0;	//원/월

}
