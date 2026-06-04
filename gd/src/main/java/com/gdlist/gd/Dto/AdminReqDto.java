package com.gdlist.gd.Dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record AdminReqDto(
        @NotNull(message = "Valores nulos não são permitidos")
        @NotBlank
        String name,

        @NotNull(message = "Valores nulos não são permitidos")
        @NotBlank
        String description,

        @Min(1)
        @Max(250)
        Integer position,

        @NotNull(message = "Valores nulos não são permitidos")
        @NotBlank
        @URL(message = "URL inválida")
        @Pattern(regexp = "^(?:http|https)://.*$")
        String image,


        @NotNull(message = "Valores nulos não são permitidos")
        @NotBlank
        @URL(message = "URL inválida")
        @Pattern(regexp = "^(?:http|https)://.*$")
        String video) {
}
