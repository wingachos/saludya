package view;

import model.Doctor;

/** Representación pública de un doctor en las respuestas de la API. */
public record DoctorView(Long id, String nombres, String apellidos, String especialidad,
                         String email, String telefono, boolean activo) {
    public static DoctorView desde(Doctor doctor) {
        return new DoctorView(doctor.getId(), doctor.getNombres(), doctor.getApellidos(),
                doctor.getEspecialidad(), doctor.getEmail(), doctor.getTelefono(), doctor.isActivo());
    }
}
