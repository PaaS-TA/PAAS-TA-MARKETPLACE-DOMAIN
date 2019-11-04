package org.openpaas.paasta.marketplace.api.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private Long planGuid;

    @CreatedDate
    protected LocalDateTime createdDate = LocalDateTime.now();
}
