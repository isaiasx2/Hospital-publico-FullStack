package med.vold.api.domain.paciente;

import med.vold.api.domain.direccion.DatosDireccion;

public record DatosRespuestaPaciente(Long id, String nombre, String documento, DatosDireccion direccion) {

}
