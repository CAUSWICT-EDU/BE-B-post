package edu.causwict.restapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

// 이전에 엔티티 이름을 User로 했다가 어디랑 이름이 겹쳐서 Member로 변경
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String name;

    @Column
    Timestamp created_at;

    @Column
    Timestamp deleted_at;
}
