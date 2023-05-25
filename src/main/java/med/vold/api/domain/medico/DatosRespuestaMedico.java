package med.vold.api.domain.medico;

import med.vold.api.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(Long id, String email, String telefono, String documento, DatosDireccion direccion) {

}
