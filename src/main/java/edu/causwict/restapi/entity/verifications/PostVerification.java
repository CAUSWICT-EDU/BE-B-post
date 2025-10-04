package edu.causwict.restapi.entity.verifications;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.entity.enums.ErrorCode;
import edu.causwict.restapi.repository.InMemoryPostRepository;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class PostVerification {

    /**
     * 주어진 Post가 규칙에 맞는지 확인합니다.
     *
     * @param post 대상 Post
     * @param repository Repository 인스턴스
     * @return 오류 코드를 반환합니다. 만약 오류가 없다면 {@code null}을 반환합니다.
     */
    @Nullable
    public static ErrorCode verify(Post post, InMemoryPostRepository repository) {
        if(post.getTitle().isEmpty()) {
            return ErrorCode.TITLE_IS_EMPTY;
        }
        if(post.getTitle().length() > 30) {
            return ErrorCode.TITLE_IS_TOO_LONG;
        }

        List<Post> postList = repository.findAll();
        if(postList.stream().map(Post::getTitle)
                .collect(Collectors.toSet()).contains(post.getTitle())) {
            return ErrorCode.TITLE_ALREADY_EXIST;
        }

        if(repository.getLastGenerated() != null) {
            Duration duration = Duration.between(repository.getLastGenerated(), post.getGenerated());
            System.out.println(duration.toString());
            if(duration.toMinutes() < 3 && duration.toHours() < 1 && duration.toDays() < 1) {
                return ErrorCode.SPAMMING;
            }
        }

        return null;
    }
}
