package edu.causwict.restapi.domain;

import edu.causwict.restapi.domain.enums.SubjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private SubjectType type;

    @Column
    private Timestamp created_at;

    @Column
    private Timestamp deleted_at;
}
