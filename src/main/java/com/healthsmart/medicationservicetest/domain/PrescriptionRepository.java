package com.healthsmart.medicationservicetest.domain;

import java.util.Optional;
public interface PrescriptionRepository {

    Iterable<Prescription> findAllPrescriptions();

    Prescription save(Prescription prescription);

    Optional<Prescription> findByPrescriptionName(String medicationname);

    boolean existsByPrescriptionName(String medicationname);

    void deleteByPrescriptionName(String medicationname);

}
