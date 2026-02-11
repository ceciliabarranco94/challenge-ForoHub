package com.aluracursos.challenge_ForoHub.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank String titulo, //debe enviarse en el json
        @NotBlank String mensaje, //debe enviarse en el json
        @NotBlank String autor, //debe enviarse en el json
        @NotNull Curso curso //debe enviarse en el json
        ) {
}
