package com.healthsmart.medicationservicetest.domain;

public class PrescriptionNotFoundException extends RuntimeException{
    public PrescriptionNotFoundException(String medicationname) {
        super("The prescription with the name: " + medicationname + " was not found.");
    }
}
