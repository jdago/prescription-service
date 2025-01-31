package com.healthsmart.medicationservicetest.web;

import com.healthsmart.medicationservicetest.domain.Prescription;
import com.healthsmart.medicationservicetest.domain.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService){
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public Iterable<Prescription> findAllPrescriptions() {
        return prescriptionService.viewPrescriptionList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prescription post(@RequestBody Prescription prescription) {
        return prescriptionService.addPrescriptionForPatient(prescription);
    }

    @DeleteMapping({"{medicationname}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String medicationname) {
        prescriptionService.removePrescriptionFromPatient(medicationname);
    }

    @GetMapping("{medicationname}")
    public Prescription getByPrescriptionName(@PathVariable String medicationname) {
        return prescriptionService.viewPrescriptionDetails(medicationname);
    }

    @PatchMapping("{medicationname}")
    public Prescription put(@PathVariable String medicationname, @RequestBody Prescription prescription) {
        return prescriptionService.editPrescriptionDetails(medicationname, prescription);
    }
}
