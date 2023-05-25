package med.vold.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.vold.api.domain.direccion.DatosDireccion;

public record DatosRegistroPaciente(


        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        String documento,
        @NotBlank
        String telefono,
        @NotNull
        @Valid
        DatosDireccion direccion
        )
{


}
