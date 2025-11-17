package edu.causwict.restapi.domain;

import edu.causwict.restapi.domain.common.BaseEntity;
import edu.causwict.restapi.domain.enums.SubjectType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 63)
    private String name;

    @Enumerated(EnumType.STRING)
    private SubjectType type;

    @Column
    private LocalDateTime deleted_at;
}
