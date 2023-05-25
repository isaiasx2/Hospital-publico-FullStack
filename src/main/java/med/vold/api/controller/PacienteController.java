package med.vold.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vold.api.domain.direccion.DatosDireccion;
import med.vold.api.domain.paciente.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity <DatosRespuestaPaciente> registrarPaciente (@RequestBody @Valid DatosRegistroPaciente datosRegistrarPaciente,
    UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = pacienteRepository.save(new Paciente(datosRegistrarPaciente));
        DatosRespuestaPaciente datosRespuestaPaciente = new DatosRespuestaPaciente(paciente.getId(),
                paciente.getNombre(),
                paciente.getDocumento(),
                new DatosDireccion(paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento()));

        URI url = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPaciente);


    }
    @GetMapping
    @Transactional
    public ResponseEntity <Page<DatosListadoPaciente>> listaPacientes(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(pacienteRepository.findAll(paginacion).map(DatosListadoPaciente::new ));

    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente){

            Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
            paciente.actualizarDatos(datosActualizarPaciente);
            return ResponseEntity.ok( new DatosRespuestaPaciente(paciente.getId(),
                    paciente.getNombre(),
                    paciente.getDocumento(),
                    new DatosDireccion(paciente.getDireccion().getCalle(),
                            paciente.getDireccion().getDistrito(),
                            paciente.getDireccion().getCiudad(),
                            paciente.getDireccion().getNumero(),
                            paciente.getDireccion().getComplemento())));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarPaciente(@PathVariable Long id){

        Paciente paciente = pacienteRepository.getReferenceById(id);
        pacienteRepository.delete(paciente);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity <DatosRespuestaPaciente> retornarDatosPaciente(@PathVariable Long id){

        Paciente paciente = pacienteRepository.getReferenceById(id);
        var datosPaciente = new DatosRespuestaPaciente(paciente.getId(),
                paciente.getNombre(),
                paciente.getDocumento(),
                new DatosDireccion(paciente.getDireccion().getCalle(),
                        paciente.getDireccion().getDistrito(),
                        paciente.getDireccion().getCiudad(),
                        paciente.getDireccion().getNumero(),
                        paciente.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosPaciente);

    }




}
