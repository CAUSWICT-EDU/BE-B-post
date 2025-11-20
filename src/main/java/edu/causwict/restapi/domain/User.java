package edu.causwict.restapi.domain;

import jakarta.persistence.*;
import lombok.*;
import edu.causwict.restapi.domain.Comment;
import edu.causwict.restapi.domain.common.BaseEntity;
import edu.causwict.restapi.domain.Post;
import edu.causwict.restapi.domain.Vote;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 255)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Vote> votes = new ArrayList<>();
}