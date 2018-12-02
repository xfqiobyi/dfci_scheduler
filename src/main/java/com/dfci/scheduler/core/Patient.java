package com.dfci.scheduler.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patient")
@NamedQueries(
        {
                @NamedQuery(
                        name = "com.dfci.scheduler.core.Patient.findAll",
                        query = "SELECT p FROM Patient p"
                )
        })
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "medical_record_number", nullable = false)
    private long medicalRecordNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    public Patient(long medicalRecordNumber, String firstName, String lastName) {
        this.medicalRecordNumber = medicalRecordNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(long medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                medicalRecordNumber == patient.medicalRecordNumber &&
                firstName.equals(patient.firstName) &&
                lastName.equals(patient.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicalRecordNumber, firstName, lastName);
    }
}
