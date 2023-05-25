package med.vold.api.domain.medico;

public record DatosListadoMedico(Long id, String nombre, String documento, String email) {

    public DatosListadoMedico(Medico medico){
        this( medico.getId(), medico.getNombre(),medico.getDocumento(),medico.getEmail());
    }



}
