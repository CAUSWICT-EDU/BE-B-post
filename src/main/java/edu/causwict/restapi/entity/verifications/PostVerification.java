package edu.causwict.restapi.entity.verifications;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.entity.enums.ErrorCode;
import edu.causwict.restapi.repository.InMemoryPostRepository;
import edu.causwict.restapi.utils.GraphemeLengthUtil;
import edu.causwict.restapi.utils.TimeUtil;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class PostVerification {

    /**
     * 게시글 사이 최소 시간 간격입니다. 단위는 초(s)입니다.
     */
    private static final long SPAMMING_DETECTION_TIME = TimeUtil.toSecond(0, 3);

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

        if(GraphemeLengthUtil.getGraphemeLength(post.getTitle()) > 30) {
            return ErrorCode.TITLE_IS_TOO_LONG;
        }

        List<Post> postList = repository.findAll();
        // 게시물 수정 시 자기 자신의 제목은 제외하도록 함. 생성 시에는 id가 null 이므로 해당 없음.
        if(postList.stream().filter(p -> !p.getId().equals(post.getId())).map(Post::getTitle)
                .collect(Collectors.toSet()).contains(post.getTitle())) {
            return ErrorCode.TITLE_ALREADY_EXIST;
        }

        if(repository.getLastGenerated() != null) {
            Duration duration = Duration.between(repository.getLastGenerated(), post.getGenerated());
            if(duration.compareTo(Duration.ofSeconds(SPAMMING_DETECTION_TIME)) < 0) {
                return ErrorCode.SPAMMING;
            }
        }

        return null;
    }

}
