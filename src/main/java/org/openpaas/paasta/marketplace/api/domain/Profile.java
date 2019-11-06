package org.openpaas.paasta.marketplace.api.domain;

import java.time.LocalDateTime;

import javax.persistence.*;
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

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime statusModifiedDate;

    public enum Type {
       Public, Company, Personal, Etc
    }

    public enum Status {
        Request("요청"), Approval("승인"), Rejected("반려");

        private String name;

        Status(String name) {
            this.name = name;
        }

        public String getName() { // 문자를 받아오는 함수
            return name;
        }
    };
}
