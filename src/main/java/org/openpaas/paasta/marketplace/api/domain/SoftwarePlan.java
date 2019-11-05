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

    private Integer cpuAmt;		//원/월

    private Integer memoryAmt;	//원/월

    private Integer diskAmt;	//원/월

    public interface Update {
    }

    @Override
    public String toString() {
        return "SoftwarePlan{" +
                "id=" + id +
                ", softwareId=" + softwareId +
                ", applyMonth='" + applyMonth + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", memorySize='" + memorySize + '\'' +
                ", diskSize='" + diskSize + '\'' +
                ", cpuAmt=" + cpuAmt +
                ", memoryAmt=" + memoryAmt +
                ", diskAmt=" + diskAmt +
                '}';
    }
}
