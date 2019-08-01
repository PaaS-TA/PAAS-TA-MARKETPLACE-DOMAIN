package org.openpaas.paasta.marketplace.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Profile extends AbstractEntity {

    @Id
    private String id;

    @Column(unique = true)
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @NotNull
    private Type type;

    private String manager;

    private String email;

    private String siteUrl;

    public enum Type {
       Public, Company, Personal, Etc
    }

}
