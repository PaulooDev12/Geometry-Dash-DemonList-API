package com.gdlist.gd.Dto;

public record AdminResponse(
        String name,
        String description,
        Integer position,
        String image,
        String video,
        String id
) {
}
