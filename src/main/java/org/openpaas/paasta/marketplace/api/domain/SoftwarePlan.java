package org.openpaas.paasta.marketplace.api.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SoftwarePlan extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "software_id")
    private Long softwareId;

    private String applyMonth;	//yyyyMM

    private String name;

    private String description;

    private String memorySize;

    private String diskSize;

    private Integer cpuAmt = 0;		//원/월

    private Integer memoryAmt = 0;	//원/월

    private Integer diskAmt = 0;	//원/월

}
