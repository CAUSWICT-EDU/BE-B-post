package edu.causwict.restapi.domain;

import jakarta.persistence.*;
import lombok.*;
import edu.causwict.restapi.domain.common.BaseEntity;
import edu.causwict.restapi.domain.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList<>();
}
