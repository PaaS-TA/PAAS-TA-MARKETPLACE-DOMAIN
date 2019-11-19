package org.openpaas.paasta.marketplace.api.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author hrjin
 * @version 1.0
 * @since 2019-10-31
 */
@Data
@Entity
public class TestSoftwareInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long softwareId;

    private String appGuid;

//    private Long planGuid;
    
    private Long softwarePlanId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    protected LocalDateTime createdDate = LocalDateTime.now();

    public enum Status {
        Successful, Failed
    }
}
