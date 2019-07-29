package org.openpaas.paasta.marketplace.api.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Software extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank(groups = { Create.class, Update.class })
    private String name;

    @ManyToOne
    @NotNull(groups = { Create.class, Update.class })
    private Category category;

    @Enumerated(EnumType.STRING)
    @NotBlank(groups = { UpdateStatus.class })
    private Status status;

    @NotNull(groups = { Create.class, Update.class })
    private String app;

    @NotNull(groups = { Create.class, Update.class })
    private String manifest;

    private String icon;

    @OneToMany
    private List<Screenshot> screenshotList;

    @Lob
    private String summary;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(groups = { Create.class, Update.class })
    private Type type;

    @NotNull(groups = { Create.class, Update.class })
    private Long pricePerDay;

    @NotNull(groups = { Create.class, Update.class })
    private String version;

    @Lob
    private String confirmComment;

    public enum Status {
        Pending, Approval, Rejected
    };

    public enum Type {
        Api, Web
    };

    public interface Create {
    }

    public interface Update {
    }

    public interface UpdateStatus {
    }

}
