package com.aluracursos.challenge_ForoHub.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        String status,
        Curso curso
) {
}
