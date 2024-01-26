package services;

import entities.Patient;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PatientService {
    private static PatientService instance;
    private Map<Integer,Patient> patients = new HashMap<>();

    public static PatientService getInstance() {
        if (instance == null) {
            instance = new PatientService();
        }
        return instance;
    }

    public Patient addPatient(Patient patient) {
        int id = patients.size()+1;
        patient.setId(id);
        patients.put(id, patient);
        return patient;
    }

    public Patient getPatient(int id) {
        return patients.get(id);
    }

    public Set<Patient> getAllPatients() {
        return new HashSet<>(patients.values());
    }

    public Patient updatePatient(Patient patient) {
        int id = patient.getId();
        if (patients.containsKey(id)) {
            patients.put(id, patient);
            return patient;
        }
        return null;
    }

    public boolean deletePatient(int id) {
        return patients.remove(id) != null;
    }
}