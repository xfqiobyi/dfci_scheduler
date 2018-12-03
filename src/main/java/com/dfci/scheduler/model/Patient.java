package com.dfci.scheduler.model;

import com.dfci.scheduler.model.serialization.LocalDateDeserializer;
import com.dfci.scheduler.model.serialization.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "patient")
@NamedQueries(
    {
        @NamedQuery(
            name = "com.dfci.scheduler.model.Patient.findAll",
            query = "SELECT p FROM Patient p"
        ),
        @NamedQuery(
                name = "com.dfci.scheduler.model.Patient.find",
                query = "SELECT p FROM Patient p WHERE firstName = :fName AND lastName = :lName AND dateOfBirth = :dob"
        )
    })
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "medical_record_number", nullable = false)
    private long medicalRecordNumber;

    @Column(name = "first_name", nullable = false)
    @JsonProperty()
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    public Patient() {
        // default constructor required for deserialization
    }

    public Patient(long medicalRecordNumber, String firstName, String lastName, LocalDate dateOfBirth) {
        this.medicalRecordNumber = medicalRecordNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
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

    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonDeserialize(using = LocalDateDeserializer.class)
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id &&
                medicalRecordNumber == patient.medicalRecordNumber &&
                firstName.equals(patient.firstName) &&
                lastName.equals(patient.lastName) &&
                dateOfBirth.equals(patient.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicalRecordNumber, firstName, lastName, dateOfBirth);
    }
}
