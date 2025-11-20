package edu.causwict.restapi.entity;

import edu.causwict.restapi.entity.common.BaseEntity;
import edu.causwict.restapi.entity.mapping.Comment;
import edu.causwict.restapi.entity.mapping.PostLike;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 15)
    private String nickname;

    @Column(nullable = false, length = 40) // 비밀번호는 암호화되니까 좀 길게
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PostLike> likes = new ArrayList<>();
}
