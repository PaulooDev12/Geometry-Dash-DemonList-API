package com.gdlist.gd.Dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record AdminReqDto(
        @Nullable
//        @NotNull(message = "Valores nulos não são permitidos")
//        @NotBlank
        String name,
        @Nullable
//        @NotNull(message = "Valores nulos não são permitidos")
//        @NotBlank
        String description,

        @Min(1)
        @Max(250)
        @Nullable
        Integer position,

//        @NotNull(message = "Valores nulos não são permitidos")
//        @NotBlank
        String image,

        @Nullable
//        @NotNull(message = "Valores nulos não são permitidos")
//        @NotBlank
//        @URL(message = "URL inválida")
//        @Pattern(regexp = "^(?:http|https)://.*$")
        String video) {
}
