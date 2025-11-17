package edu.causwict.restapi.domain;

import edu.causwict.restapi.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

// 이전에 엔티티 이름을 User로 했다가 어디랑 이름이 겹쳐서 Member로 변경
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column
    private LocalDateTime deleted_at;
}
