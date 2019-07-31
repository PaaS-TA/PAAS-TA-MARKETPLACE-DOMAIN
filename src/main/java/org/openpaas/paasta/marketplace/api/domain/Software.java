package org.openpaas.paasta.marketplace.api.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
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
    @NotBlank(groups = { Create.class, Update.class, UpdateMetadata.class })
    private String name;

    @ManyToOne
    @NotNull(groups = { Create.class, Update.class, UpdateMetadata.class })
    private Category category;

    @Enumerated(EnumType.STRING)
    @NotNull(groups = { UpdateMetadata.class })
    private Status status;

    @NotNull(groups = { Create.class, Update.class })
    private String app;

    @NotNull(groups = { Create.class, Update.class })
    private String manifest;

    private String icon;

    @ElementCollection
    private List<String> screenshotList;

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

    @Enumerated(EnumType.STRING)
    @NotNull(groups = { Create.class, Update.class, UpdateMetadata.class })
    protected Yn inUse;

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

    public interface UpdateMetadata {
    }

    @PrePersist
    @Override
    public void prePersist() {
    }

    public boolean canUse() {
        return status == Status.Approval && inUse == Yn.Y;
    }
}
