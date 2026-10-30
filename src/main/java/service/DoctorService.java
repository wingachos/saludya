package service;

import exception.DuplicateEmailException;
import exception.ResourceNotFoundException;
import model.Doctor;
import dao.DoctorRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DoctorService {
    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public List<Doctor> listar() { return repository.findAll(); }

    @Transactional(readOnly = true)
    public Doctor obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor no encontrado: " + id));
    }

    public Doctor crear(Doctor doctor) {
        if (repository.existsByEmailIgnoreCase(doctor.getEmail())) throw new DuplicateEmailException(doctor.getEmail());
        doctor.setId(null);
        return repository.save(doctor);
    }

    public Doctor actualizar(Long id, Doctor datos) {
        Doctor doctor = obtener(id);
        if (repository.existsByEmailIgnoreCaseAndIdNot(datos.getEmail(), id)) throw new DuplicateEmailException(datos.getEmail());
        doctor.setNombres(datos.getNombres());
        doctor.setApellidos(datos.getApellidos());
        doctor.setEspecialidad(datos.getEspecialidad());
        doctor.setEmail(datos.getEmail());
        doctor.setTelefono(datos.getTelefono());
        doctor.setActivo(datos.isActivo());
        return repository.save(doctor);
    }

    public void eliminar(Long id) {
        Doctor doctor = obtener(id);
        repository.delete(doctor);
    }
}

