package com.gdlist.gd.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record AdminReqDto(
        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String description,

        @Min(1)
        Integer position,

        @NotNull
        @NotBlank
        @URL
        String image,


        @NotNull
        @NotBlank
        @URL
        String video) {
}
