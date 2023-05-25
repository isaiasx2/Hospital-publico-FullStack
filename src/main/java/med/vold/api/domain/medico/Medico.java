package med.vold.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vold.api.domain.direccion.Direccion;

@Entity(name="medico")
@Table(name="medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;
    private Boolean activo;

    public Medico(DatosRegistroMedico datosRegistrarMedico) {
        this.activo = true;
    this.nombre = datosRegistrarMedico.nombre();
    this.email = datosRegistrarMedico.email();
    this.documento = datosRegistrarMedico.documento();
    this.especialidad = datosRegistrarMedico.especialidad();
    this.direccion = new Direccion(datosRegistrarMedico.direccion());
    this.telefono = datosRegistrarMedico.telefono();
    }
    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico){
        if(datosActualizarMedico.nombre() != null){
            this.nombre = datosActualizarMedico.nombre();
        }
        if(datosActualizarMedico.documento() != null){
            this.documento = datosActualizarMedico.documento();
        }
        if(datosActualizarMedico.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }

    }

    public void desactivarMedico() {
       this.activo = false;
    }
}
