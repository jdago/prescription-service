package com.healthsmart.medicationservicetest;

import com.healthsmart.medicationservicetest.domain.Prescription;
import com.healthsmart.medicationservicetest.domain.PrescriptionAlreadyExistsException;
import com.healthsmart.medicationservicetest.domain.PrescriptionNotFoundException;
import com.healthsmart.medicationservicetest.domain.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository){
        this.prescriptionRepository = prescriptionRepository;
    }
    public Iterable<Prescription> viewPrescriptionList() {
        return prescriptionRepository.findAllPrescriptions();
    }
    public Prescription addPrescriptionForPatient(Prescription prescription) {
        if (prescriptionRepository.existsByPrescriptionName(prescription.medicationname())) {
            throw new PrescriptionAlreadyExistsException(prescription.medicationname());
        }
        return prescriptionRepository.save(prescription);
    }

    public Optional<Prescription> viewPrescriptionByName(String medicationname) {
        return prescriptionRepository.findByPrescriptionName(medicationname);
    }

    public Prescription editPrescriptionDetails(String medicationname, Prescription prescription) {
        return prescriptionRepository.findByPrescriptionName(medicationname)
                .map(existingPrescription -> {

                    double updateddosage = prescription.dosageinmilligrams() != 0 ? prescription.dosageinmilligrams() : existingPrescription.dosageinmilligrams();
                    String updatedinstructions = prescription.dosageinstructions() != null ? prescription.dosageinstructions() : existingPrescription.dosageinstructions();
                    List<String> updatedwarnings = prescription.specialwarnings() != null ? prescription.specialwarnings() : existingPrescription.specialwarnings();

                    var updatedPrescription = new Prescription(
                            existingPrescription.patientid(),
                            existingPrescription.prescriptionid(),
                            existingPrescription.medicationname(),
                            existingPrescription.medicationclass(),
                            updateddosage,
                            existingPrescription.quantity(),
                            existingPrescription.administrationroute(),
                            updatedinstructions,
                            updatedwarnings,
                            existingPrescription.allergens(),
                            existingPrescription.prescribeddate(),
                            existingPrescription.expirationdate(),
                            existingPrescription.iscontrolled(),
                            existingPrescription.isexpired()
                    );

                    return prescriptionRepository.save(updatedPrescription);
                })
                .orElseThrow(() -> new PrescriptionNotFoundException(medicationname));
    }
    public void removePrescriptionFromPatient(String medicationname) {
        prescriptionRepository.deleteByPrescriptionName(medicationname);
    }

    public Iterable<Prescription> viewExpiredPrescriptionList() {
        return prescriptionRepository.findAllExpiredPrescriptions();
    }


}
