package com.gdlist.gd.Dto;

public record ResponseDto(
        String name,

        String description,

        String image,

        Integer position,

        String video
) {
}