package com.healthsmart.medicationservicetest.domain;

public class PrescriptionAlreadyExistsException extends RuntimeException {
    public PrescriptionAlreadyExistsException(String medicationname){
        super("The prescription with the name: " + medicationname + " already exists.");
    }

}
