package org.openpaas.paasta.marketplace.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Category extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull(groups = { Create.class, Update.class })
    @Size(min = 1, max = 50, groups = { Create.class, Update.class, UpdateName.class })
    private String name;

    @Lob
    private String description;

    private Long seq;

    public enum Direction {
        Up, Down
    }

    public interface Create {
    }

    public interface Update {
    }

    public interface UpdateName {
    }

}
