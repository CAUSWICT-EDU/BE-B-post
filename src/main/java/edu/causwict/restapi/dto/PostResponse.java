package edu.causwict.restapi.dto;

import edu.causwict.restapi.entity.Post; // ⬅️ Post(엔티티) import

public record PostResponse(
        Long id,
        String title,
        String content
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent()
        );
    }
}