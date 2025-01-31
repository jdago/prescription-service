package com.healthsmart.medicationservicetest.domain;

import java.time.LocalDate;
import java.util.List;

public record Prescription (
    String patientid,
    String prescriptionid,
    String medicationname,
    String medicationclass,
    double dosageinmilligrams,
    int quantity,
    String administrationroute,
    String dosageinstructions,
    List<String> specialwarnings,
    List<String> allergens,
    LocalDate prescribeddate,
    LocalDate expirationdate,
    boolean iscontrolled,
    boolean isexpired
){}

