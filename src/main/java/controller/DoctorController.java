package controller;

import model.Doctor;
import service.DoctorService;
import view.DoctorView;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {
    private final DoctorService service;

    public DoctorController(DoctorService service) { this.service = service; }

    @GetMapping
    public List<DoctorView> listar() { return service.listar().stream().map(DoctorView::desde).toList(); }

    @GetMapping("/{id}")
    public DoctorView obtener(@PathVariable Long id) { return DoctorView.desde(service.obtener(id)); }

    @PostMapping
    public ResponseEntity<DoctorView> crear(@Valid @RequestBody Doctor doctor) {
        Doctor creado = service.crear(doctor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(creado.getId()).toUri();
        return ResponseEntity.created(location).body(DoctorView.desde(creado));
    }

    @PutMapping("/{id}")
    public DoctorView actualizar(@PathVariable Long id, @Valid @RequestBody Doctor doctor) { return DoctorView.desde(service.actualizar(id, doctor)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

