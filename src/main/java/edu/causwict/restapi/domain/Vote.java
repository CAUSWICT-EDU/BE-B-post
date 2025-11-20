package edu.causwict.restapi.domain;

import jakarta.persistence.*;
import lombok.*;
import edu.causwict.restapi.domain.common.BaseEntity;
import edu.causwict.restapi.domain.Post;
import edu.causwict.restapi.domain.User;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Vote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "duration")
    private LocalDate duration;

    @Column(name = "result")
    private Boolean result;
}