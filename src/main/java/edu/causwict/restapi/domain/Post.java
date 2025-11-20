package edu.causwict.restapi.domain;

import jakarta.persistence.*;
import lombok.*;
import edu.causwict.restapi.domain.Board;
import edu.causwict.restapi.domain.Comment;
import edu.causwict.restapi.domain.common.BaseEntity;
import edu.causwict.restapi.domain.User;
import edu.causwict.restapi.domain.Vote;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;

    private Long viewCount;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Vote> votes = new ArrayList<>();
}