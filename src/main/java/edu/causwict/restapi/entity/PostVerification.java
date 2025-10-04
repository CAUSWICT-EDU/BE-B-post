package edu.causwict.restapi.entity;

import edu.causwict.restapi.entity.enums.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

public class PostVerification {
    private static final PostVerification instance = new PostVerification();

    public static PostVerification getInstance() {
        return instance;
    }

    // 확인
    public ErrorCode verify(Post post, List<Post> postList) {
        if(post.getTitle().isEmpty()) {
            return ErrorCode.TITLE_IS_EMPTY;
        }
        if(post.getTitle().length() > 30) {
            return ErrorCode.TITLE_IS_TOO_LONG;
        }
        if(postList.stream().map(Post::getTitle)
                .collect(Collectors.toSet()).contains(post.getTitle())) {
            return ErrorCode.TITLE_ALREADY_EXIST;
        }

        return ErrorCode.NO_ERROR;
    }
}
