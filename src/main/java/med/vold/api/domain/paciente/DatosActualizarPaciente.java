package med.vold.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import med.vold.api.domain.direccion.DatosDireccion;

public record DatosActualizarPaciente(@NotNull Long id, String nombre, String email, DatosDireccion direccion) {



}
