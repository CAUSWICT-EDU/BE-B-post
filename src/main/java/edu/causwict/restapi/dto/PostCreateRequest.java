package edu.causwict.restapi.dto;

public record PostCreateRequest(
        String title,
        String content
) { }
