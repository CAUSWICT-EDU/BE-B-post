package edu.causwict.restapi.entity.mapping;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.entity.User;
import edu.causwict.restapi.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "post_like",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_post_like_user_post",
                        columnNames = {"user_id", "post_id"} // 한 유저가 한 게시글에 좋아요는 한 번만!
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLike extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}