package app.week05.TheVeterinarian.handlers;


import app.week05.TheVeterinarian.dtos.PatientDTO;
import java.util.HashMap;
import java.util.Map;

public class PatientHandler {
    private Map<Integer, PatientDTO> patients = new HashMap<>();

    public PatientHandler() {
        patients.put(1, new PatientDTO(1, "Fido", "Dog", "Golden Retriever", "Annual checkup"));
        patients.put(2, new PatientDTO(2, "Rex", "Dog", "German Shepherd", "Vaccination"));
        patients.put(3, new PatientDTO(3, "Buddy", "Dog", "Labrador Retriever", "Dental cleaning"));
        patients.put(4, new PatientDTO(4, "Max", "Dog", "Beagle", "Surgery"));
        patients.put(5, new PatientDTO(5, "Rocky", "Dog", "Bulldog", "X-ray"));
    }

    public Map<Integer, PatientDTO> getAllPatients() {
        return patients;
    }

    public PatientDTO getPatientById(int id) {
        return patients.get(id);
    }
}
