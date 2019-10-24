package org.openpaas.paasta.marketplace.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class SoftwarePlanHistory extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Software software;

    @Lob
    private String description;

    private String applyMonth;

    private String name;

    private String memorySize;

    private String diskSize;

    private Integer cpuAmt;

    private Integer memoryAmt;

    private Integer diskAmt;


}
