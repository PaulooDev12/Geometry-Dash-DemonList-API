package com.gdlist.gd.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminReqDto(
        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String description,

        Integer position,

        @NotNull
        @NotBlank
        String image,



        String video) {
}
