package com.healthsmart.medicationservicetest.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import com.healthsmart.medicationservicetest.domain.Prescription;
import com.healthsmart.medicationservicetest.domain.PrescriptionRepository;
import org.springframework.stereotype.Repository;
@Repository
public class InMemoryPrescriptionRepository implements PrescriptionRepository {
    private static final Map<String, Prescription> prescriptions =
            new ConcurrentHashMap<>();

    @Override
    public Iterable<Prescription> findAllPrescriptions() {
        return prescriptions.values();
    }
    @Override
    public Prescription save(Prescription prescription) {
        prescriptions.put(prescription.medicationname(), prescription);
        return prescription;
    }
    @Override
    public Optional<Prescription> findByPrescriptionName(String medicationname) {
        return existsByPrescriptionName(medicationname) ? Optional.of(prescriptions.get(medicationname)) : Optional.empty();
    }
    @Override
    public boolean existsByPrescriptionName(String medicationname){
        return prescriptions.get(medicationname) != null;
    }
    @Override
    public void deleteByPrescriptionName(String medicationname) {
         prescriptions.remove(medicationname);
    }
}
